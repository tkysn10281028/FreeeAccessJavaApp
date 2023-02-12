package com.sono.process.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sono.process.dto.methodutils.ClassInfoDto;
import com.sono.process.dto.methodutils.MethodInfoDto;

/**
 * メソッドを処理するクラス。
 * 
 * @author entakuya
 */
@Component
public class MethodUtilsBusinessLogic implements BusinessLogic {
	Logger logger = LoggerFactory.getLogger(MethodUtilsBusinessLogic.class);
	private Scanner scanner = null;
	private String inputWord;
	private Integer presentCursor = 0;

	/**
	 * 各事前実行・メイン処理・最終処理の実行順番の制御及びビジネスロジックのエントリポイント
	 * 
	 * @see 抽象度：AAA
	 */
	public void executeMethodUtils() {
		this.beforeProcess();
		this.mainProcess();
		this.afterProcess();
	}

	/**
	 * 事前実行処理を実施 文字列の読み取りを行い、メイン処理使用の文字列を格納する
	 * 
	 * @see 抽象度:AA
	 */
	@Override
	public void beforeProcess() {
		logger.info("started beforeProcess in MethodUtilsBusinessLogic");
		initEnvAndPrintFirstLog();
		this.inputWord = scanner.next();
	}

	/**
	 * メイン処理を実施 事前処理の文字列を読み取って処理する
	 * 
	 * @see 抽象度：AA
	 */
	@Override
	public void mainProcess() {
		logger.info("started mainProcess in MethodUtilsBusinessLogic");
		readInputWordAndPrint(this.inputWord);
	}

	/**
	 * 事後処理を実施
	 * 
	 * @see 抽象度：AA
	 */
	@Override
	public void afterProcess() {
		logger.info("started afterProcess in MethodUtilsBusinessLogic");
	}

	/**
	 * 事前処理として余計な文字列の除去を行い、文字列を処理
	 * 
	 * @param inputWord 入力文字列
	 * @see 抽象度：A
	 */
	private void readInputWordAndPrint(String inputWord) {
		var inputWordList = splitInputWordBySpaceAndFilterBlank(replaceWordIntoSpace(this.inputWord));
		var classInfo = generateClassInfo(inputWordList);
		this.presentCursor = 0;
		var methodList = generateMethodInfoList(classInfo.getClassPart());
	}

	/**
	 * 与えられた文字列の情報からクラス情報DTOを作成して返却
	 * 
	 * @param inputWordList 事前処理済みの文字列リスト
	 * @return 文字列から読み取ったクラス情報DTO
	 * @see 抽象度：B
	 */
	private ClassInfoDto generateClassInfo(List<String> inputWordList) {
		var classInfo = new ClassInfoDto();
		for (int i = 0; i < inputWordList.size(); i++) {
			var inputWord = inputWordList.get(i);
			// classのキーワードかつリストの最後ではない場合
			if ((inputWord.equals("class") || inputWord.equals("interface")) && i < inputWordList.size() - 1) {
				classInfo.setClassName(inputWordList.get(i + 1));
				var leftBracketAfterList = generateSplitListAfterPart(inputWordList, i);
				var classPartList = generateSplitListByFromAndAfterNum(leftBracketAfterList,
						getFirstCursorCountLeftBracket(leftBracketAfterList),
						getLastCursorCountRightBracket(leftBracketAfterList));
				classInfo.setClassPart(classPartList);
			}
		}
		if (StringUtils.isEmpty(classInfo.getClassName())) {
			throw new IllegalArgumentException("No Class Found.");
		}
		return classInfo;
	}

	/**
	 * クラス内部で存在しているメソッドのリストDTOを返却
	 * 
	 * @param classInfoPart
	 * @return
	 * @see 抽象度：B
	 */
	private List<MethodInfoDto> generateMethodInfoList(List<String> classInfoPart) {
		var methodInfoDtoList = new ArrayList<MethodInfoDto>();
		var info = generateMethodInfoListImplements(classInfoPart, 0);
		return methodInfoDtoList;
	}

	/**
	 * リストからメソッド情報のリストを返却
	 * 
	 * @param targetlist 取得元のリスト
	 * @param index      リストの個別要素取得用インデックス
	 * @return メソッド情報リスト
	 * @see 抽象度：Bー
	 */
	private List<MethodInfoDto> generateMethodInfoListImplements(List<String> targetlist, Integer startIndex) {
		var methodList = new ArrayList<MethodInfoDto>();
		var methodInfo = new MethodInfoDto();
//		for (int i = startIndex; i < targetlist.size(); i++) {
//			var targetString = targetlist.get(i);
//			if (judgeIsRightBracket(targetString) == null) {
//				continue;
//			} else if (judgeIsRightBracket(targetString)) {
//
//				var list = generateSplitListByFromAndAfterNum(targetlist, startIndex, i);
//				methodInfo.setMethodPart(list);
//				this.presentCursor = i;
//				return methodInfo;
//			} else if (!judgeIsRightBracket(targetString)) {
//				if (i < targetlist.size() - 1) {
//					methodInfo = generateMethodInfoListImplements(targetlist, i + 1);
//					methodList.add(methodInfo);
//					i = i < targetlist.size() ? this.presentCursor + 1 : this.presentCursor;
//					continue;
//				} else {
//					continue;
//				}
//			}
//		}
		return methodList;
	}

	private List<MethodInfoDto> generateMethodImplementsLoop(List<String> targetlist, Integer startIndex) {
		var methodInfoDtoList = new ArrayList<MethodInfoDto>();
		for (int i = startIndex; i < targetlist.size(); i++) {
			var target = targetlist.get(i);
			if (judgeIsRightBracket(target) == null) {
				continue;
			} else if (judgeIsRightBracket(target)) {
				var methodInfoDto = new MethodInfoDto();
				var methodPartList = generateSplitListByFromAndAfterNum(targetlist, startIndex, i - 1);
				methodInfoDto.setMethodPart(methodPartList);
				methodInfoDto.setMethodInfoList(methodInfoDtoList);
				methodInfoDtoList.clear();
				methodInfoDtoList.add(methodInfoDto);
				break;
			} else if (!judgeIsRightBracket(target)) {
				var returnList = generateMethodImplementsLoop(targetlist, i);
				returnList.forEach(dto -> {
					methodInfoDtoList.add(dto);
				});
				continue;
			}
		}
		return methodInfoDtoList;
	}

	/**
	 * 文字列のリストを受け取って「{」の文字がいればそのインデックスを返却 最初から数える
	 * 
	 * @param inputWordList 文字列リスト
	 * @return {が出現するリストのインデックス
	 * @see 抽象度：C
	 */
	private Integer getFirstCursorCountLeftBracket(List<String> inputWordList) {
		var firstCursorCount = 0;
		for (int i = 0; i < inputWordList.size(); i++) {
			if (inputWordList.get(i).equals("{")) {
				firstCursorCount = i + 1;
				break;
			}
		}
		return firstCursorCount;
	}

	/**
	 * 文字列のリストを受け取って「}」の文字がいればそのインデックスを返却 最後から数える
	 * 
	 * @param inputWordList
	 * @return }が出現するリストのインデックス
	 * @see 抽象度：C
	 */
	private Integer getLastCursorCountRightBracket(List<String> inputWordList) {
		var lastCursorCount = 0;
		for (int i = inputWordList.size() - 1; i >= 0; i--) {
			if (inputWordList.get(i).equals("}")) {
				lastCursorCount = i;
				break;
			}
		}
		return lastCursorCount;
	}

	/**
	 * 「{」「}」を検知する
	 * 
	 * @param inputWord
	 * @return {の時false }の時true それ以外はnull
	 * @see 抽象度：C
	 */
	private Boolean judgeIsRightBracket(String inputWord) {
		if (inputWord.equals("{")) {
			return false;
		} else if (inputWord.equals("}")) {
			return true;
		} else {
			return null;
		}
	}

	/**
	 * 与えられたリストの最初の位置を現在の読み込み位置に指定
	 * 
	 * @param list 抽象度：D
	 */
	private void setCursorFromFirstObjectInList(List<String> list) {

	}

	/**
	 * リストを与えられたインデックスの後半部分に切り取る
	 * 
	 * @param list     切り取り対象リスト
	 * @param splitNum 切り取りする最初の位置
	 * @return 切り取ったリスト
	 * @see 抽象度：D
	 */
	private List<String> generateSplitListAfterPart(List<String> list, Integer splitNum) {
		if (splitNum >= list.size()) {
			throw new IllegalArgumentException("Cannot Split List.");
		}
		return new ArrayList<String>(list.subList(splitNum, list.size()));
	}

	/**
	 * リストを指定された位置で切り取りする
	 * 
	 * @param list     切り取り対象のリスト
	 * @param fromNum  切り取りのスタート位置
	 * @param AfterNum 切り取りの終了位置
	 * @return 切り取ったリスト
	 * @see 抽象度：D
	 */
	private List<String> generateSplitListByFromAndAfterNum(List<String> list, Integer fromNum, Integer AfterNum) {
		return new ArrayList<String>(list.subList(fromNum, AfterNum));
	}

	/**
	 * 特定の単語をスペースに変換する
	 * 
	 * @param 変換対象
	 * @return 変換後
	 * @see 抽象度：D
	 */
	private String replaceWordIntoSpace(String inputWord) {
		return inputWord.replaceAll("\n", " ").replaceAll("\t", " ");
	}

	/**
	 * 空白部分の除去とスペースで単語を区切ってリストに変換
	 * 
	 * @param inputWord 変換対象
	 * @return 変換後
	 * @see 抽象度：D
	 */
	private List<String> splitInputWordBySpaceAndFilterBlank(String inputWord) {
		return Arrays.asList(inputWord.split(" ")).stream().filter(word -> !word.equals(""))
				.collect(Collectors.toList());
	}

	/**
	 * 本処理の初期処理
	 * 
	 * @see 抽象度：D
	 */
	private void initEnvAndPrintFirstLog() {
		this.scanner = new Scanner(System.in);
		this.scanner.useDelimiter(Pattern.compile(";;;;"));
		System.out.println("Input Your Program Here->");
	}

	/**
	 * 本処理の終了処理
	 * 
	 * @see 抽象度：D
	 */
	private void closeEnv() {
		this.scanner.close();
	}

	/**
	 * デバッグ用メソッド
	 * 
	 * @param obj
	 * @see 抽象度：D
	 */
	private void evacuationForDebug(Object obj) {
		var evacObj = obj;
	}
}

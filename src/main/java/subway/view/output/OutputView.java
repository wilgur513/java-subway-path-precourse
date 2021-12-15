package subway.view.output;

import subway.domain.PathResult;

public class OutputView {
	private static final String HEADER_PREFIX = "## ";
	private static final String INFO_PREFIX = "[INFO] ";
	private static final String ERROR_PREFIX = "[ERROR] ";

	public static void printMainPage() {
		printHeader("메인 화면");
		printOption("1", "경로 조회");
		printOption("Q", "종료");
		printBlankLine();
	}

	public static void printSelectPath() {
		printHeader("경로 기준");
		printOption("1", "최단 거리");
		printOption("2", "최소 시간");
		printOption("B", "돌아가기");
		printBlankLine();
	}

	public static void printPathResult(PathResult pathResult) {
		printHeader("조회 결과");
		printInfo("---");
		printInfo("총 거리: " + pathResult.getTotalDistance() + "km");
		printInfo("총 소요 시간: " + pathResult.getTotalTime() + "분");
		printInfo("---");
		printPath(pathResult);
		printBlankLine();
	}

	public static void printErrorMessage(String message) {
		printError(message);
		printBlankLine();
	}

	private static void printHeader(String header) {
		System.out.println(HEADER_PREFIX + header);
	}

	private static void printOption(String option, String name) {
		System.out.println(option + ". " + name);
	}

	private static void printBlankLine() {
		System.out.println();
	}

	private static void printInfo(String message) {
		System.out.println(INFO_PREFIX + message);
	}

	private static void printError(String message) {
		System.out.println(ERROR_PREFIX + message);
	}

	private static void printPath(PathResult pathResult) {
		pathResult
			.getPath()
			.stream()
			.forEach(OutputView::printInfo);
	}
}

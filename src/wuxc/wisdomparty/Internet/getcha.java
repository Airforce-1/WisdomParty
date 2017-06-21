package wuxc.wisdomparty.Internet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getcha {
	static String reg = "\\<[^>]*\\}";

	public static String gethan(String args) {// 获取汉字，去除格式
		String result = "";
		// Pattern p = null;
		// Matcher m = null;
		// String value = null;
		// p = Pattern.compile("([/u4e00-/u9fa5]+)");
		// m = p.matcher(args);
		// while (m.find() && value.length() < 50) {
		// value = m.group(0);
		//
		// }
		String htmlStr = args; // 含html标签的字符串
		String textStr = "";
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
		Pattern p_html;
		Matcher m_html;
		Pattern pl;
		Matcher ml;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			pl = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
			ml = pl.matcher(htmlStr);
			htmlStr = ml.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

			textStr = textStr.replace("&nbsp;", "");
			textStr = textStr.replace("\n", "");
			textStr = textStr.replace("\t", "");
			textStr = textStr.replace("\r", "");
			textStr = textStr.replace(" ", "");
			textStr = textStr.replace("	", "");

		} catch (Exception e) {
			// Manager.log.debug("neiNewsAction", "Html2Text: " +
			// e.getMessage());
		}
		return textStr;// 返回文本字符串
		// Regex regex = new Regex("<.+?>", RegexOptions.IgnoreCase);
		// String strOutput = regex.Replace(args, "");//替换掉"<"和">"之间的内容
		// strOutput = strOutput.Replace("<", "");
		// strOutput = strOutput.Replace(">", "");
		// strOutput = strOutput.Replace("&nbsp;", "");
		// return strOutput;
		//
		// StringBuffer bf = new StringBuffer();// 用于追加子串
		//
		// char[] chars = args.toCharArray();
		// for (int i = 0; i < chars.length; i++) {
		// if (chars[i] > 127 || (47 < chars[i] && chars[i] < 58)) {
		// // 汉字是两个字节,判断汉字可以如下:第一个字节大于127并且第二个字节也
		// // 大于127,则是汉字,否则不是.
		// // System.out.println(chars[i] + "---ASCII--- " +
		// // Integer.toHexString(chars[i])); //可以打印出ASCII码
		// // System.out.println(chars[i]);
		// bf.append(chars[i]);
		// }
		// }

		// return bf.toString();
	}
}

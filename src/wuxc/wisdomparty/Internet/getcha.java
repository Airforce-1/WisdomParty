package wuxc.wisdomparty.Internet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getcha {
	static String reg = "\\<[^>]*\\}";

	public static String gethan(String args) {// ��ȡ���֣�ȥ����ʽ
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
		String htmlStr = args; // ��html��ǩ���ַ���
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
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // ����script��������ʽ{��<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // ����style��������ʽ{��<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // ����script��ǩ

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // ����style��ǩ

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // ����html��ǩ

			pl = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
			ml = pl.matcher(htmlStr);
			htmlStr = ml.replaceAll(""); // ����html��ǩ

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
		return textStr;// �����ı��ַ���
		// Regex regex = new Regex("<.+?>", RegexOptions.IgnoreCase);
		// String strOutput = regex.Replace(args, "");//�滻��"<"��">"֮�������
		// strOutput = strOutput.Replace("<", "");
		// strOutput = strOutput.Replace(">", "");
		// strOutput = strOutput.Replace("&nbsp;", "");
		// return strOutput;
		//
		// StringBuffer bf = new StringBuffer();// ����׷���Ӵ�
		//
		// char[] chars = args.toCharArray();
		// for (int i = 0; i < chars.length; i++) {
		// if (chars[i] > 127 || (47 < chars[i] && chars[i] < 58)) {
		// // �����������ֽ�,�жϺ��ֿ�������:��һ���ֽڴ���127���ҵڶ����ֽ�Ҳ
		// // ����127,���Ǻ���,������.
		// // System.out.println(chars[i] + "---ASCII--- " +
		// // Integer.toHexString(chars[i])); //���Դ�ӡ��ASCII��
		// // System.out.println(chars[i]);
		// bf.append(chars[i]);
		// }
		// }

		// return bf.toString();
	}
}

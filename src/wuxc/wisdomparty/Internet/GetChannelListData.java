package wuxc.wisdomparty.Internet;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import android.content.SharedPreferences;

public class GetChannelListData {

	public static String[] GetChannelListDataBySign(SharedPreferences PreUserInfo, String chn, int curPage,
			int pageSize) {
		String ticket = "";
		ticket = PreUserInfo.getString("ticket", "");
		String Result = "";
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		ArrayValues.add(new BasicNameValuePair("chn", chn));
		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));

		Result = HttpGetData.GetData("api/cms/channel/channleListData", ArrayValues);

		return null;

	}

}

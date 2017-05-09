package wuxc.wisdomparty.Internet;

import android.content.SharedPreferences;

public class GetChannelByKey {
	// private SharedPreferences PreALLChannel;// 存储所用频道信息
	// PreALLChannel = getSharedPreferences("ALLChannel", Context.MODE_PRIVATE);
	public static String GetSign(SharedPreferences PreALLChannel, String Key) {

		try {
			for (int i = 0; i < 999; i++) {
				// Log.e("GetSign","GetSign" + i);
				String keyid = "NoData";
				keyid = PreALLChannel.getString("AC_name" + i, "NoData");
				if (keyid.equals("NoData")) {
					break;
				}
				if (keyid.equals(Key)) {
					String Result = "0204";
					Result = PreALLChannel.getString("AC_sign" + i, "0204");
					return Result;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "0204";

	}

	public static String GetUrl(SharedPreferences PreALLChannel, String Key) {

		try {
			for (int i = 0; i < 999; i++) {
				// Log.e("GetUrl","GetUrl" + i);
				String keyid = "NoData";
				keyid = PreALLChannel.getString("AC_name" + i, "NoData");
				if (keyid.equals("NoData")) {
					break;
				}
				if (keyid.equals(Key)) {
					String Result = "0204";
					Result = PreALLChannel.getString("AC_url" + i, "0204");
					return Result;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "0204";

	}
}

package wuxc.wisdomparty.layout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Bitmap.Config;
import android.media.ExifInterface;
import android.renderscript.FieldPacker;

public class PictureUtils {

	/**
	 * ѹ��ͼƬ��
	 * 
	 * @param path
	 *            ͼƬ·��
	 * @param size
	 *            ͼƬ���ߴ�
	 * @return ѹ�����ͼƬ
	 * @throws IOException
	 */
	public static Bitmap compressImage(String path, int size) throws IOException {
		Bitmap bitmap = null;
		// ȡ��ͼƬ
		InputStream is = new FileInputStream(path);
		BitmapFactory.Options options = new BitmapFactory.Options();
		// �������������Ϊbitmap�����ڴ�ռ䣬ֻ��¼һЩ��ͼƬ����Ϣ������ͼƬ��С����˵���˾���Ϊ���ڴ��Ż�
		options.inJustDecodeBounds = true;
		// ͨ������ͼƬ�ķ�ʽ��ȡ��options�����ݣ��������������java�ĵ�ַ��������ֵ��
		BitmapFactory.decodeStream(is, null, options);
		// �ر���
		is.close();

		// // ����ѹ����ͼƬ
		int i = 0;
		while (true) {
			// ��һ���Ǹ���Ҫ���õĴ�С��ʹ��͸߶�������
			if ((options.outWidth >> i <= size) && (options.outHeight >> i <= size)) {
				// ����ȡ������ע�⣺����һ��Ҫ�ٴμ��أ����ܶ���ʹ��֮ǰ������
				is = new FileInputStream(path);
				// ���������ʾ �����ɵ�ͼƬΪԭʼͼƬ�ļ���֮һ��
				options.inSampleSize = (int) Math.pow(2.0D, i);
				// ����֮ǰ����Ϊ��true������Ҫ��Ϊfalse������ʹ�������ͼƬ
				options.inJustDecodeBounds = false;
				options.inPreferredConfig = Config.ARGB_8888;
				// ͬʱ���òŻ���Ч
				options.inPurgeable = true;
				// ��ϵͳ�ڴ治��ʱ��ͼƬ�Զ�������
				options.inInputShareable = true;
				// ����Bitmap
				bitmap = BitmapFactory.decodeStream(is, null, options);
				break;
			}
			i += 1;
		}

		return bitmap;
	}

	/**
	 * ����ͼƬ���̶��ļ���С
	 * 
	 * @param bm
	 *            ��Ҫ���ŵ�ͼƬ
	 * @param maxSize
	 *            Ŀ���ļ���С����λ��KB
	 * @return
	 */
	public static Bitmap imageZoom(Bitmap bm, double maxSize) {
		// ͼƬ�������ռ� ��λ��KB
		// ��bitmap���������У�����bitmap�Ĵ�С����ʵ�ʶ�ȡ��ԭ�ļ�Ҫ��
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		// ���ֽڻ���KB
		double mid = b.length / 1024;
		// �ж�bitmapռ�ÿռ��Ƿ�����������ռ� ���������ѹ�� С����ѹ��
		if (mid > maxSize) {
			// ��ȡbitmap��С ����������С�Ķ��ٱ�
			double i = mid / maxSize;
			// ��ʼѹ�� �˴��õ�ƽ���� ������͸߶�ѹ������Ӧ��ƽ������
			// ���̶ֿȺ͸߶Ⱥ�ԭbitmap����һ�£�ѹ����Ҳ�ﵽ������Сռ�ÿռ�Ĵ�С
			bm = zoomImage(bm, bm.getWidth() / Math.sqrt(i), bm.getHeight() / Math.sqrt(i));
		}
		return bm;
	}

	/***
	 * ͼƬ�����ŷ���
	 * 
	 * @param bgimage
	 *            ��ԴͼƬ��Դ
	 * @param newWidth
	 *            �����ź���
	 * @param newHeight
	 *            �����ź�߶�
	 * @return
	 */
	public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
		// ��ȡ���ͼƬ�Ŀ�͸�
		float width = bgimage.getWidth();
		float height = bgimage.getHeight();
		// ��������ͼƬ�õ�matrix����
		Matrix matrix = new Matrix();
		// ������������
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// ����ͼƬ����
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
		return bitmap;
	}

	/**
	 * 
	 * @Title: rotateImage
	 * @param path
	 * @return void
	 */
	public static int getImageOrientation(String path) {

		try {
			ExifInterface exifInterface = new ExifInterface(path);

			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			return orientation;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return ExifInterface.ORIENTATION_NORMAL;
	}

	public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
		Matrix m = new Matrix();
		if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
			m.setRotate(90);
		} else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
			m.setRotate(180);
		} else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
			m.setRotate(270);
		} else {
			return bitmap;
		}
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		try {
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);
		} catch (OutOfMemoryError ooe) {

			m.postScale(1, 1);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);

		}
		return bitmap;
	}
}

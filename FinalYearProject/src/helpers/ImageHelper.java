package helpers;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import globals.FileNames;

public class ImageHelper {

	//gets images
	public static Image GetImageResource(String filePath) {
		ImageIcon imgIcon = new ImageIcon(filePath);
		return imgIcon.getImage();
	}

	public Image RotateImage(KeyEvent ke) {
		Image img = null;
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_W:
			img = ImageHelper.GetImageResource(FileNames.PLAYER_UP_GRAPHIC_FILE_PATH);
			break;
		case KeyEvent.VK_A:
			img = ImageHelper.GetImageResource(FileNames.PLAYER_LEFT_GRAPHIC_FILE_PATH);
			break;
		case KeyEvent.VK_S:
			img = ImageHelper.GetImageResource(FileNames.PLAYER_DOWN_GRAPHIC_FILE_PATH);
			break;
		case KeyEvent.VK_D:
			img = ImageHelper.GetImageResource(FileNames.PLAYER_RIGHT_GRAPHIC_FILE_PATH);
			break;
		}

		return img;
	}

	//resize image
	private static Image ResizeImage(Image srcImg, int w, int h) {
		BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImage.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImage;
	}
	
	//gets the image and returns the resized image
	public static ImageIcon GetImageResize(String filePath, int w, int h){ 
		Image img = GetImageResource(filePath);
		return new ImageIcon(ResizeImage(img, w, h));
	}
}

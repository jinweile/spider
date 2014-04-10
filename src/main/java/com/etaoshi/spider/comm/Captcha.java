package com.etaoshi.spider.comm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 图片验证码类
 */
public class Captcha {
	private int width = 100;
	private int height = 30;
	private Random random = new Random();
	private BufferedImage image;
	/** 验证码图片上显示的字符 */
	private String code;
	/** 波形的幅度倍数，越大扭曲的程序越高，一般为3 */
	private int twistLevel = 2;
	/** 干扰线数量 */
	private int noiseLineNumber = 3;
	/** 背景色 */
	private Color backgroundColor = Color.GREEN;
	/** 字体颜色 */
	private Color[] colors = { Color.BLUE };

	private Color getRandomColor() {
		return colors[random.nextInt(colors.length)];
	}

	/**
	 * @param width
	 *            - 验证码图片宽度
	 * @param height
	 *            - 验证码图片高度
	 * @param randomStr
	 *            - 随机字符串
	 * @return
	 */
	public Captcha generate(int width, int height, String randomStr) {
		this.width = width;
		this.height = height;
		this.code = randomStr;
		if (code.isEmpty()) {
			throw new RuntimeException("randomStr can not be empty.");
		}
		int xWidth = width / (code.length() + 2);
		int yIndex = height - 4;
		Graphics2D graphics = graphicsInit();
		for (int i = 0; i < code.length(); i++) {
			graphics.setColor(getRandomColor());
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			graphics.drawString(code.charAt(i) + "", (i + 1) * xWidth, yIndex);
		}
		setBuffImg(disturb());
		return this;
	}

	private Graphics2D graphicsInit() {
		Graphics2D graphics = buffImgInit().createGraphics();
		graphics.setColor(backgroundColor);
		graphics.fillRect(0, 0, width, height);
		graphics.setFont(new Font("Fixedsys", Font.ITALIC, height - 2));
		graphics.drawRect(0, 0, width - 1, height - 1);
		return graphics;
	}

	private BufferedImage buffImgInit() {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		return image;
	}

	private BufferedImage disturb() {
		drawNoiseLine(image.createGraphics());
		return twistImage();
	}

	private void drawNoiseLine(Graphics2D graphics) {
		int x = 0;
		int y = 0;
		int xl = 0;
		int yl = 0;
		for (int i = 0; i < noiseLineNumber; i++) {
			x = random.nextInt(width * 2 / 3);
			y = random.nextInt(height * 2 / 3);
			xl = random.nextInt(width / 2);
			yl = random.nextInt(height / 2);
			graphics.setColor(getRandomColor());
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			graphics.drawLine(x, y, x + xl, y + yl);
		}
	}

	private BufferedImage twistImage() {
		double dMultValue = random.nextInt(9) + twistLevel;
		double dPhase = random.nextInt(6);// 波形的起始相位，取值区间（0-2＊PI）
		BufferedImage destBi = new BufferedImage(image.getWidth(),
				image.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = destBi.createGraphics();
		graphics.setColor(backgroundColor);
		graphics.fillRect(0, 0, width, height);
		for (int i = 0; i < destBi.getWidth(); i++) {
			for (int j = 0; j < destBi.getHeight(); j++) {
				int nOldX = getXPosition4Twist(dPhase, dMultValue,
						destBi.getHeight(), i, j);
				int nOldY = j;
				if (nOldX >= 0 && nOldX < destBi.getWidth() && nOldY >= 0
						&& nOldY < destBi.getHeight()) {
					destBi.setRGB(nOldX, nOldY, image.getRGB(i, j));
				}
			}
		}
		return destBi;
	}

	private int getXPosition4Twist(double dPhase, double dMultValue,
			int height, int xPosition, int yPosition) {
		double PI = 0;//Math.PI; // 此值越大，扭曲程度越大
		double dx = (double) (PI * yPosition) / height + dPhase;
		double dy = Math.sin(dx);
		return xPosition + (int) (dy * dMultValue);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setBuffImg(BufferedImage buffImg) {
		this.image = buffImg;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}

package com.skillrisers.gaming.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class KenPlayer extends Sprite {
	private BufferedImage walkImages[] = new BufferedImage[6];
	private BufferedImage kickImages[] = new BufferedImage[6];
	private BufferedImage punchImages[] = new BufferedImage[6];
	BufferedImage damageEffectImages[] = new BufferedImage[5];

	public KenPlayer() throws IOException {
		x = GWIDTH - 400;
		h = 200;
		w = 200;
		y = FLOOR - h;
		speed = 0;
		image = ImageIO.read(RyuPlayer.class.getResource(KEN_IMAGE));
		loadWalkImages();
		loadKickImages();
		loadPunchImages();
		loadDamageEffect();
	}

	public void loadDamageEffect() {
		damageEffectImages[0] = image.getSubimage(1365, 3276, 65, 95);
		damageEffectImages[1] = image.getSubimage(1437, 3271, 88, 99);
		damageEffectImages[2] = image.getSubimage(1537, 3278, 75, 91);
		damageEffectImages[3] = image.getSubimage(1627, 3277, 70, 92);
		damageEffectImages[4] = image.getSubimage(1712, 3274, 63, 98);
	}

	public BufferedImage printDamageImage() {
		if (imageIndex > damageEffectImages.length - 1) {
			imageIndex = 0;
			currentMove = WALK;
		}
		BufferedImage img = damageEffectImages[imageIndex];
		imageIndex++;
		return img;

	}

	public void jump() {
		if (!isJump) {
			isJump = true;
			force = -20;
			y = y + force;
		}
	}

	public void fall() {
		if (y >= (FLOOR - h)) {
			isJump = false;
			return;
		}
		y = y + force;
		force = force + GRAVITY;
	}

	private void loadWalkImages() {
		walkImages[0] = image.getSubimage(2033, 680, 64, 98);
		walkImages[1] = image.getSubimage(1683, 680, 70, 98);
		walkImages[2] = image.getSubimage(1756, 680, 70, 98);
		walkImages[3] = image.getSubimage(1828, 680, 70, 98);
		walkImages[4] = image.getSubimage(1896, 680, 70, 99);
		walkImages[5] = image.getSubimage(1965, 680, 70, 96);
	}

	private void loadKickImages() {
		kickImages[0] = image.getSubimage(1692, 1563, 68, 98);
		kickImages[1] = image.getSubimage(1770, 1563, 68, 98);
		kickImages[2] = image.getSubimage(1838, 1563, 116, 98);
		kickImages[3] = image.getSubimage(1838, 1563, 116, 98);
		kickImages[4] = image.getSubimage(2035, 1563, 61, 98);
		kickImages[5] = image.getSubimage(1692, 1563, 68, 98);
	}

	private void loadPunchImages() {
		punchImages[0] = image.getSubimage(1588, 1146, 76, 98);
		punchImages[1] = image.getSubimage(1666, 1146, 110, 98);
		// punchImages[2] = image.getSubimage(1666, 1146, 110, 98);
		punchImages[2] = image.getSubimage(1785, 1146, 76, 98);
		punchImages[3] = image.getSubimage(1865, 1146, 66, 98);
		punchImages[4] = image.getSubimage(1932, 1146, 97, 98);
		punchImages[5] = image.getSubimage(2028, 1146, 68, 98);
	}

	private BufferedImage printWalk() {
		if (imageIndex > 5) {
			imageIndex = 0;
		}
		BufferedImage img = walkImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}

	private BufferedImage printKick() {
		if (imageIndex > 5) {
			imageIndex = 0;
			currentMove = WALK;
			isAttacking = false;
		}
		isAttacking = true;
		BufferedImage img = kickImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}

	private BufferedImage printPunch() {
		if (imageIndex > 5) {
			imageIndex = 0;
			currentMove = WALK;
			isAttacking = false;
		}
		isAttacking = true;
		BufferedImage img = punchImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}

	@Override
	public BufferedImage defaultImage() {
		// BufferedImage subImage = image.getSubimage(1756, 685, 62, 94);
		// return subImage;
		if (currentMove == KICK) {
			return printKick();
		} else if (currentMove == PUNCH) {
			return printPunch();
		} else {
			return printWalk();
		}
	}

}

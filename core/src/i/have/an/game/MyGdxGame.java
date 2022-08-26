package i.have.an.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {

	SpriteBatch batch;

	TextureRegion[] playerIdleRegion = new TextureRegion[14];
	Animation<TextureRegion> playerIdleAnimation;

	TextureRegion[] playerWalkRegion = new TextureRegion[15];
	Animation<TextureRegion> playerWalkAnimation;

	TextureRegion[] playerRunRegion = new TextureRegion[14];
	Animation<TextureRegion> playerRunAnimation;

	TextureRegion[] playerJumpRegion = new TextureRegion[15];
	Animation<TextureRegion> playerJumpAnimation;

	int playerX = 250;
	int playerY = 50;
	int playerWidth = 70;
	int playerHeight = 120;

	float gravity = 0;

	boolean playerWalkRight = false;
	boolean playerWalkLeft = false;
	boolean playerWalkUp = false;
	boolean playerWalkDown = false;

	boolean playerRun = false;

	boolean playerRightStand = true;

	float time = 0;

	@Override
	public void create () {

		batch = new SpriteBatch();

		setPlayerIdleTexture();
		setPlayerWalkTexture();
		setPlayerRunTexture();
		setPlayerJumpTexture();

		playerIdleAnimation = new Animation<>(0.05f, playerIdleRegion);
		playerWalkAnimation = new Animation<>(0.05f, playerWalkRegion);
		playerRunAnimation = new Animation<>(0.05f, playerRunRegion);
		playerJumpAnimation = new Animation<>(0.05f, playerJumpRegion);
	}

	@Override
	public void render () {

		time+=Gdx.graphics.getDeltaTime();

		ScreenUtils.clear(0, 1, 0, 1);
		batch.begin();
		if(playerWalkRight){
			if(playerRun){
				if(playerRightStand) {
					batch.draw(playerRunAnimation.getKeyFrame(time, true), playerX, playerY, playerWidth, playerHeight);
				}else{
					Sprite playerTextureFlip = new Sprite(playerRunAnimation.getKeyFrame(time,true));
					playerTextureFlip.flip(true,false);
					batch.draw(playerTextureFlip, playerX, playerY, playerWidth, playerHeight);
				}
			}else{
				if(playerRightStand) {
					batch.draw(playerWalkAnimation.getKeyFrame(time, true), playerX, playerY, playerWidth, playerHeight);
				}else{
					Sprite playerTextureFlip = new Sprite(playerWalkAnimation.getKeyFrame(time,true));
					playerTextureFlip.flip(true,false);
					batch.draw(playerTextureFlip, playerX, playerY, playerWidth, playerHeight);
				}
			}

		}else if(playerWalkLeft){
			if(playerRun){
				if(playerRightStand) {
					batch.draw(playerRunAnimation.getKeyFrame(time, true), playerX, playerY, playerWidth, playerHeight);
				}else{
					Sprite playerTextureFlip = new Sprite(playerRunAnimation.getKeyFrame(time,true));
					playerTextureFlip.flip(true,false);
					batch.draw(playerTextureFlip, playerX, playerY, playerWidth, playerHeight);
				}
			}else{
				if(playerRightStand) {
					batch.draw(playerWalkAnimation.getKeyFrame(time, true), playerX, playerY, playerWidth, playerHeight);
				}else{
					Sprite playerTextureFlip = new Sprite(playerWalkAnimation.getKeyFrame(time,true));
					playerTextureFlip.flip(true,false);
					batch.draw(playerTextureFlip, playerX, playerY, playerWidth, playerHeight);
				}
			}

		}

		if(playerWalkDown){
			if(playerRightStand) {
				batch.draw(playerIdleAnimation.getKeyFrame(time, true), playerX, playerY, playerWidth, playerHeight);
			}else{
				Sprite playerTextureFlip = new Sprite(playerIdleAnimation.getKeyFrame(time,true));
				playerTextureFlip.flip(true,false);
				batch.draw(playerTextureFlip, playerX, playerY, playerWidth, playerHeight);
			}
		}else if(playerWalkUp){
			if(playerRightStand) {
				batch.draw(playerJumpAnimation.getKeyFrame(time, true), playerX, playerY, playerWidth, playerHeight);
			}else{
				Sprite playerTextureFlip = new Sprite(playerJumpAnimation.getKeyFrame(time,true));
				playerTextureFlip.flip(true,false);
				batch.draw(playerTextureFlip, playerX, playerY, playerWidth, playerHeight);
			}
		}
		if(!playerWalkDown && !playerWalkUp && !playerWalkLeft && !playerWalkRight){
			if(playerRightStand) {
				batch.draw(playerIdleAnimation.getKeyFrame(time, true), playerX, playerY, playerWidth, playerHeight);
			}else{
				Sprite playerTextureFlip = new Sprite(playerIdleAnimation.getKeyFrame(time,true));
				playerTextureFlip.flip(true,false);
				batch.draw(playerTextureFlip, playerX, playerY, playerWidth, playerHeight);
			}
		}
		batch.end();

		controlPlayer();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void controlPlayer(){

		if(!(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.LEFT))){

			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && playerX<=Gdx.graphics.getWidth()-playerWidth) {

				if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
					playerX += 8;
					playerRun = true;
				}else{
					playerRun = false;
					playerX += 5;
				}

				if(!playerWalkUp && !playerWalkDown){
					playerWalkRight = true;
				}
				playerRightStand = false;

			}else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && playerX>=0) {

				if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
					playerX -= 8;
					playerRun = true;
				}else{
					playerRun = false;
					playerX -= 5;
				}

				if(!playerWalkDown && !playerWalkUp){
					playerWalkLeft = true;
				}
				playerRightStand = true;
				playerWalkRight = false;

			}else{
				playerWalkRight = false;
				playerWalkLeft = false;
			}
		}else{
			playerWalkRight = false;
			playerWalkLeft = false;
		}

		if(!(Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.DOWN))){

			if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && playerY>=0) {
				playerY -= 7;

				playerWalkUp = false;
				playerWalkDown = true;
				playerWalkRight = false;
				playerWalkLeft = false;
			} else if (Gdx.input.isKeyPressed(Input.Keys.UP) && playerY<=Gdx.graphics.getHeight()-playerHeight) {
				playerY += 7;

				playerWalkUp = true;
				playerWalkDown = false;
				playerWalkRight = false;
				playerWalkLeft = false;
			}else{

				playerWalkUp = false;
				playerWalkDown = false;
			}

		}else{

			playerWalkUp = false;
			playerWalkDown = false;
		}

		if(playerY>50){
			gravity+=0.2;
			playerY-=gravity;
		}else{
			gravity=0;
		}

	}

	public void setPlayerIdleTexture(){

		for(int i=0;i<14;i++){
			playerIdleRegion[i] = new TextureRegion(new Texture("flatboy\\idle\\idle ("+(i+1)+").png"));
		}
	}

	public void setPlayerWalkTexture(){

		for(int i=0;i<15;i++){
			playerWalkRegion[i] = new TextureRegion(new Texture("flatboy\\walk\\Walk ("+(i+1)+").png"));
		}
	}

	public void setPlayerRunTexture(){

		for(int i=0;i<14;i++){
			playerRunRegion[i] = new TextureRegion(new Texture("flatboy\\run\\Run ("+(i+1)+").png"));
		}
	}

	public void setPlayerJumpTexture(){

		for(int i=0;i<15;i++){
			playerJumpRegion[i] = new TextureRegion(new Texture("flatboy\\jump\\Jump ("+(i+1)+").png"));
		}
	}
}


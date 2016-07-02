package com.vallosdck.wordmob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by vallos on 6/26/2016.
 */
public class Assets implements Disposable, AssetErrorListener {

	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();

	public AssetBackground background;
	public AssetFonts fonts;
	public AssetRedX redX;
	public AssetWordBubble wordBubble;
	private AssetManager assetManager;

	private Assets() {}

	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;

		assetManager.setErrorListener(this);

		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);

		// start loading assets and wait until finished
		assetManager.finishLoading();
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames()) {
			Gdx.app.debug(TAG, "asset: " + a);
		}

		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		// enable texture filtering for pixel smoothing
		for (Texture t : atlas.getTextures()) {
			t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		}

		fonts = new AssetFonts();
		background = new AssetBackground(atlas);
		redX = new AssetRedX(atlas);
		wordBubble = new AssetWordBubble(atlas);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
		fonts.defaultSmall.dispose();
		fonts.defaultNormal.dispose();
		fonts.defaultBig.dispose();
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", throwable);
	}

	public class AssetFonts {
		public final BitmapFont defaultSmall;
		public final BitmapFont defaultNormal;
		public final BitmapFont defaultBig;

		public AssetFonts() {
			// create three fonts using Libgdx's 15px bitmap font
			defaultSmall = new BitmapFont(Gdx.files.internal("fonts/unispace-32-bold.fnt"), false);
			defaultNormal = new BitmapFont(Gdx.files.internal("fonts/unispace-32-bold.fnt"), false);
			defaultBig = new BitmapFont(Gdx.files.internal("fonts/unispace-32-bold.fnt"), false);
			// set font sizes
			defaultSmall.getData().setScale(0.50f);
			defaultNormal.getData().setScale(1.0f);
			defaultBig.getData().setScale(1.5f);
			// enable linear texture filtering for smooth fonts
			defaultSmall.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
			defaultNormal.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
			defaultBig.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
		}
	}

	public class AssetBackground {
		public final TextureAtlas.AtlasRegion background;

		public AssetBackground(TextureAtlas atlas) {
			background = atlas.findRegion("background-holder");
		}
	}

	public class AssetRedX {
		public final TextureAtlas.AtlasRegion redX;

		public AssetRedX(TextureAtlas atlas) {
			redX = atlas.findRegion("x");
		}
	}

	public class AssetWordBubble {
		public final TextureAtlas.AtlasRegion edge;
		public final TextureAtlas.AtlasRegion middle;

		public AssetWordBubble(TextureAtlas atlas) {
			edge = atlas.findRegion("bubble-edge");
			middle = atlas.findRegion("bubble-middle");
		}
	}


}

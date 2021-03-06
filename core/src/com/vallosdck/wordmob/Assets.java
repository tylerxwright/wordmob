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

	public AssetTypeWriter typeWriter;

	public AssetClock clock;
	public AssetFonts fonts;
	public AssetRedX redX;
	public AssetStar star;
	public AssetUiBar uiBar;
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

		typeWriter = new AssetTypeWriter(atlas);
		clock = new AssetClock(atlas);
		fonts = new AssetFonts();
		redX = new AssetRedX(atlas);
		star = new AssetStar(atlas);
		uiBar = new AssetUiBar(atlas);
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
		public final BitmapFont brainFlowerNormal;
		public final BitmapFont momsTypewriterNormal;

		public AssetFonts() {
			// create three fonts using Libgdx's 15px bitmap font
			defaultSmall = new BitmapFont(Gdx.files.internal("fonts/unispace-32-bold.fnt"), false);
			defaultNormal = new BitmapFont(Gdx.files.internal("fonts/unispace-32-bold.fnt"), false);
			defaultBig = new BitmapFont(Gdx.files.internal("fonts/unispace-32-bold.fnt"), false);
			brainFlowerNormal = new BitmapFont(Gdx.files.internal("fonts/brain-flower-46.fnt"), false);
			momsTypewriterNormal = new BitmapFont(Gdx.files.internal("fonts/moms-typewriter-24.fnt"), false);

			// set font sizes
			defaultSmall.getData().setScale(0.50f);
			defaultNormal.getData().setScale(1.0f);
			defaultBig.getData().setScale(1.5f);

			// enable linear texture filtering for smooth fonts
			defaultSmall.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
			defaultNormal.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
			defaultBig.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
			brainFlowerNormal.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
			momsTypewriterNormal.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		}
	}

	public class AssetTypeWriter {
		public final TextureAtlas.AtlasRegion guide;
		public final TextureAtlas.AtlasRegion bottom;

		public AssetTypeWriter(TextureAtlas atlas) {
			guide = atlas.findRegion("typewriter-guide");
			bottom = atlas.findRegion("typewriter-bottom");
		}
	}

	public class AssetClock {
		public final TextureAtlas.AtlasRegion clock;

		public AssetClock(TextureAtlas atlas) {
			clock = atlas.findRegion("clock");
		}
	}

	public class AssetRedX {
		public final TextureAtlas.AtlasRegion redX;

		public AssetRedX(TextureAtlas atlas) {
			redX = atlas.findRegion("x");
		}
	}

	public class AssetStar {
		public final TextureAtlas.AtlasRegion starEmpty;
		public final TextureAtlas.AtlasRegion starFilled;

		public AssetStar(TextureAtlas atlas) {
			starEmpty = atlas.findRegion("star-empty");
			starFilled = atlas.findRegion("star-filled");
		}
	}

	public class AssetUiBar {
		public final TextureAtlas.AtlasRegion uiBar;

		public AssetUiBar(TextureAtlas atlas) {
			uiBar = atlas.findRegion("ui-bar");
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

package com.vallosdck.wordmob.actors;


import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vallos on 7/2/2016.
 */
public class Rating extends Group {
	private static final int PADDING = 10;

	private List<Star> stars;

	public Rating(int starRating) {
		this(3, starRating);
	}

	public Rating(int numberOfStars, int starRating) {
		stars = new ArrayList<Star>();

		if(numberOfStars < 1) {
			numberOfStars = 1;
		}

		for(int i=0; i<numberOfStars; i++) {
			Star star = new Star();
			if(i > 0) {
				star.setPosition((star.getWidth()+PADDING)*i, 0);
			}

			if(starRating >= i+1) {
				star.fillStar(true);
			}
			stars.add(star);
		}

		setWidth(stars.get(0).getWidth()*3 + PADDING*2);
		setHeight(stars.get(0).getHeight());

		for(Star star : stars) {
			addActor(star);
		}
	}
}

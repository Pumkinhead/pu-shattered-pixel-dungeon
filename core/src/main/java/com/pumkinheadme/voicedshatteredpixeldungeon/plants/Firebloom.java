/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2023 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.pumkinheadme.voicedshatteredpixeldungeon.plants;

import com.pumkinheadme.voicedshatteredpixeldungeon.Dungeon;
import com.pumkinheadme.voicedshatteredpixeldungeon.actors.Char;
import com.pumkinheadme.voicedshatteredpixeldungeon.actors.blobs.Blob;
import com.pumkinheadme.voicedshatteredpixeldungeon.actors.blobs.Fire;
import com.pumkinheadme.voicedshatteredpixeldungeon.actors.buffs.Buff;
import com.pumkinheadme.voicedshatteredpixeldungeon.actors.buffs.FireImbue;
import com.pumkinheadme.voicedshatteredpixeldungeon.actors.hero.Hero;
import com.pumkinheadme.voicedshatteredpixeldungeon.actors.hero.HeroSubClass;
import com.pumkinheadme.voicedshatteredpixeldungeon.effects.CellEmitter;
import com.pumkinheadme.voicedshatteredpixeldungeon.effects.particles.FlameParticle;
import com.pumkinheadme.voicedshatteredpixeldungeon.scenes.GameScene;
import com.pumkinheadme.voicedshatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Firebloom extends Plant {
	
	{
		image = 1;
		seedClass = Seed.class;
	}
	
	@Override
	public void activate( Char ch ) {
		
		if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
			Buff.affect(ch, FireImbue.class).set( FireImbue.DURATION*0.3f );
		}
		
		GameScene.add( Blob.seed( pos, 2, Fire.class ) );
		
		if (Dungeon.level.heroFOV[pos]) {
			CellEmitter.get( pos ).burst( FlameParticle.FACTORY, 5 );
		}
	}
	
	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.SEED_FIREBLOOM;

			plantClass = Firebloom.class;
		}
	}
}

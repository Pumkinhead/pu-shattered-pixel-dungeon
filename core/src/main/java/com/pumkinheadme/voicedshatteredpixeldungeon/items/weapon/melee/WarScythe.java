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

package com.pumkinheadme.voicedshatteredpixeldungeon.items.weapon.melee;

import com.pumkinheadme.voicedshatteredpixeldungeon.Assets;
import com.pumkinheadme.voicedshatteredpixeldungeon.actors.Char;
import com.pumkinheadme.voicedshatteredpixeldungeon.actors.hero.Hero;
import com.pumkinheadme.voicedshatteredpixeldungeon.messages.Messages;
import com.pumkinheadme.voicedshatteredpixeldungeon.sprites.ItemSpriteSheet;

public class WarScythe extends MeleeWeapon {

	{
		image = ItemSpriteSheet.WAR_SCYTHE;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 0.9f;

		tier = 5;
		ACC = 0.8f; //20% penalty to accuracy
	}

	@Override
	public int max(int lvl) {
		return  Math.round(6.67f*(tier+1)) +    //40 base, up from 30
				lvl*(tier+1);                   //scaling unchanged
	}

	public float abilityChargeUse(Hero hero, Char target) {
		return 2*super.abilityChargeUse(hero, target);
	}

	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		Sickle.harvestAbility(hero, target, 0.8f, this);
	}

}

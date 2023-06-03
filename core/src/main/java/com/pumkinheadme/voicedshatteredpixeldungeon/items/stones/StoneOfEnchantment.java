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

package com.pumkinheadme.voicedshatteredpixeldungeon.items.stones;

import com.pumkinheadme.voicedshatteredpixeldungeon.actors.hero.Belongings;
import com.pumkinheadme.voicedshatteredpixeldungeon.effects.Enchanting;
import com.pumkinheadme.voicedshatteredpixeldungeon.effects.Speck;
import com.pumkinheadme.voicedshatteredpixeldungeon.items.Item;
import com.pumkinheadme.voicedshatteredpixeldungeon.items.armor.Armor;
import com.pumkinheadme.voicedshatteredpixeldungeon.items.scrolls.exotic.ScrollOfEnchantment;
import com.pumkinheadme.voicedshatteredpixeldungeon.items.weapon.Weapon;
import com.pumkinheadme.voicedshatteredpixeldungeon.messages.Messages;
import com.pumkinheadme.voicedshatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.pumkinheadme.voicedshatteredpixeldungeon.utils.GLog;

public class StoneOfEnchantment extends InventoryStone {
	
	{
		preferredBag = Belongings.Backpack.class;
		image = ItemSpriteSheet.STONE_ENCHANT;

		unique = true;
	}

	@Override
	protected boolean usableOnItem(Item item) {
		return ScrollOfEnchantment.enchantable(item);
	}
	
	@Override
	protected void onItemSelected(Item item) {
		
		if (item instanceof Weapon) {
			
			((Weapon)item).enchant();
			
		} else {
			
			((Armor)item).inscribe();
			
		}
		
		curUser.sprite.emitter().start( Speck.factory( Speck.LIGHT ), 0.1f, 5 );
		Enchanting.show( curUser, item );
		
		if (item instanceof Weapon) {
			GLog.p(Messages.get(this, "weapon"));
		} else {
			GLog.p(Messages.get(this, "armor"));
		}
		
		useAnimation();
		
	}
	
	@Override
	public int value() {
		return 30 * quantity;
	}

	@Override
	public int energyVal() {
		return 4 * quantity;
	}

}

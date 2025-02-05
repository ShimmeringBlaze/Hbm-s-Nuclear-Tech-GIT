package com.hbm.hazard.transformer;

import java.util.List;

import com.hbm.hazard.HazardEntry;
import com.hbm.hazard.HazardRegistry;
import com.hbm.hazard.HazardSystem;
import com.hbm.util.Compat;

import net.minecraft.item.ItemStack;

public class HazardTransformerRadiationME extends HazardTransformerBase {

	@Override
	public void transformPre(ItemStack stack, List<HazardEntry> entries) { }

	@Override
	public void transformPost(ItemStack stack, List<HazardEntry> entries) {
		
		if(stack.getItem().getClass().getName().equals("appeng.items.storage.ItemBasicStorageCell")) {
			List<ItemStack> stacks = Compat.scrapeItemFromME(stack);
			float radiation = 0;
			
			for(ItemStack held : stacks) {
				radiation += HazardSystem.getHazardLevelFromStack(held, HazardRegistry.RADIATION) * held.stackSize;
			}
			
			if(radiation > 0) {
				entries.add(new HazardEntry(HazardRegistry.RADIATION, radiation));
			}
		}
	}
}

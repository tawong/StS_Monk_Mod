package powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;


public class MasterOfPainPower extends AbstractPower {
	public static final String POWER_ID = "MasterOfPainPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = new TextureAtlas("img/powers/custom_powers.atlas");

	public MasterOfPainPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.priority = 0;
		updateDescription();
		this.region48 = powerAltas.findRegion("master48");
		this.region128 = powerAltas.findRegion("master128");

	}

	public int onAttacked(DamageInfo info, int damageAmount) {
		if (info.type == DamageInfo.DamageType.HP_LOSS && info.owner == this.owner && damageAmount > 0 && !this.owner.hasPower("Buffer")) {

			damageAmount *= 0.5f;
		}

		return damageAmount;
	}

	public void atEndOfTurn(boolean isPlayer) {


//		if (this.amount == 0) {
//			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "ResiliencePower"));
//		} else {
//			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "ResiliencePower", 1));
//		}
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;

		if(this.amount > 1){
			this.amount = 1;
		}
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0]);
	}
}
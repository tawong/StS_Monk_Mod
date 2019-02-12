package powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class DivinityPower extends AbstractPower {
	public static final String POWER_ID = "DivinityPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = new TextureAtlas("img/powers/custom_powers.atlas");

	public DivinityPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.priority = 0;
		updateDescription();
		this.region48 = powerAltas.findRegion("divinity48");
		this.region128 = powerAltas.findRegion("divinity128");

	}

	public void atEndOfTurn(boolean isPlayer) {
		this.flashWithoutSound();

		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DivinityPower"));
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
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0]);
	}
}
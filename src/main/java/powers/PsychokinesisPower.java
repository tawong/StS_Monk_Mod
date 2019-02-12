package powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class PsychokinesisPower extends AbstractPower {
	public static final String POWER_ID = "PsychokinesisPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = new TextureAtlas("img/powers/custom_powers.atlas");
	private int KI_BLOCK;

	public PsychokinesisPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.region48 = powerAltas.findRegion("psycho48");
		this.region128 = powerAltas.findRegion("psycho128");
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}

	public void atEndOfTurn(boolean isPlayer)
	{
		int kiAmt = 0;

		if (AbstractDungeon.player.hasPower("KiPower")) {
			kiAmt = AbstractDungeon.player.getPower("KiPower").amount;
		}

		flash();
		this.KI_BLOCK = this.amount * kiAmt;

		if (this.KI_BLOCK != 0){

			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.KI_BLOCK));
		}

	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}
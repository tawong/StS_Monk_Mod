package powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class DilationPower extends AbstractPower {
	public static final String POWER_ID = "DilationPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = new TextureAtlas("img/powers/custom_powers.atlas");
	int totaldamage = 0;

	public DilationPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.priority = 0;
		updateDescription();
		this.region48 = powerAltas.findRegion("resilience48");
		this.region128 = powerAltas.findRegion("resilience128");

	}

	public int onLoseHp(int damageAmount) {

		this.amount += damageAmount;

		if (damageAmount > 0) {
			damageAmount = 0;
		}

		return damageAmount;
	}

	public void atStartOfTurn() {
		this.flash();

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DilationDamagePower(this.owner, this.amount), this.amount));
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DilationPower"));


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
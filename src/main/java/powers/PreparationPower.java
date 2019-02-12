package powers;

import cards.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class PreparationPower extends AbstractPower {
	public static final String POWER_ID = "PreparationPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = new TextureAtlas("img/powers/custom_powers.atlas");
	private int PREP_UP;

	public PreparationPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.priority = 0;
		this.type = PowerType.DEBUFF;
		updateDescription();
		this.region48 = powerAltas.findRegion("preparation48");
		this.region128 = powerAltas.findRegion("preparation128");

		if(amount > 0){

			if(AbstractDungeon.player.hasPower("DivinityPower")){
				int divAmt = AbstractDungeon.player.getPower("DivinityPower").amount;
				AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(divAmt));
			}

			if(AbstractDungeon.player.hasPower("QuickPower")){
				int quickAmt = AbstractDungeon.player.getPower("QuickPower").amount;
				AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(quickAmt, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
			}


			if(AbstractDungeon.player.hasPower("PrimalPower")){
				int primalAmt = AbstractDungeon.player.getPower("PrimalPower").amount;

				for(int i = 0; i < primalAmt; i++) {
					int randomNum = ThreadLocalRandom.current().nextInt(1, 5 + 1);


					if (randomNum == 1) {
						AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new TigerClaw(), 1, false));
					}
					if (randomNum == 2) {
						AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new BearClaw(), 1, false));
					}
					if (randomNum == 3) {
						AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new BadgerClaw(), 1, false));
					}
					if (randomNum == 4) {
						AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new EagleClaw(), 1, false));
					}
					if (randomNum == 5) {
						AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new DragonClaw(), 1, false));
					}
					if (randomNum == 6) {
						AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new BatClaw(), 1, false));
					}

				}
			}
		}

	}

	public void atStartOfTurn()
	{
		flash();
		this.PREP_UP = this.amount;

		if (this.PREP_UP != 0)
			AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(this.PREP_UP));

		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "PreparationPower"));
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;

//		if(stackAmount > 0){
//
//			if(AbstractDungeon.player.hasPower("DivinityPower")){
//				int divAmt = AbstractDungeon.player.getPower("DivinityPower").amount;
//				AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(divAmt));
//			}
//
//			if(AbstractDungeon.player.hasPower("QuickPower")){
//				int quickAmt = AbstractDungeon.player.getPower("QuickPower").amount;
//				AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(quickAmt, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
//			}
//
//
//			if(AbstractDungeon.player.hasPower("PrimalPower")){
//				int primalAmt = AbstractDungeon.player.getPower("PrimalPower").amount;
//
//				for(int i = 0; i < primalAmt; i++) {
//					int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
//
//
//					if (randomNum == 1) {
//						AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new TigerClaw(), 1, false));
//					}
//					if (randomNum == 2) {
//						AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new BearClaw(), 1, false));
//					}
//					if (randomNum == 3) {
//						AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new BadgerClaw(), 1, false));
//					}
//					if (randomNum == 4) {
//						AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new EagleClaw(), 1, false));
//					}
//					if (randomNum == 5) {
//						AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new BatClaw(), 1, false));
//					}
//					if (randomNum == 6) {
//						AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new DragonClaw(), 1, false));
//					}
//
//				}
//			}
//
//		}

	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}
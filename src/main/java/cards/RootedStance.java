package cards;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.HeavyBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import patches.AbstractCardEnum;
import powers.KiPower;

import java.util.Iterator;

public class RootedStance extends CustomCard {
	public static final String ID = "RootedStance";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int BLOCK = 5;
	private static final int BLOCK_UP = 3;

	public RootedStance() {
		super(ID, NAME, "img/cards/rootedstance.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.COMMON,
				CardTarget.SELF);
		this.baseBlock = this.block = BLOCK;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int KiAmt = 0;

		if (AbstractDungeon.player.hasPower("KiPower")){
			KiAmt = p.getPower("KiPower").amount;

			for(int i = 0; i < KiAmt; ++i) {
				AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
			}

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, -KiAmt), -KiAmt));
		}
	}

	public AbstractCard makeCopy() {
		return new RootedStance();
	}


	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(BLOCK_UP);
		}
	}
}
package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import patches.AbstractCardEnum;

public class ForearmBlock extends CustomCard {
	public static final String ID = "ForearmBlock";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 12;
	private static final int BLOCK_UP = 3;
	private static final int HP_LOSS = 2;

	public ForearmBlock() {
		super(ID, NAME, "img/cards/forearmblock.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
				CardTarget.SELF);
		this.baseBlock = this.block = BLOCK;
		this.baseMagicNumber = this.magicNumber = HP_LOSS;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, HP_LOSS));
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));

	}



	public AbstractCard makeCopy() {
		return new ForearmBlock();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(BLOCK_UP);
		}
	}
}
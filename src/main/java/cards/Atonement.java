package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import patches.AbstractCardEnum;
import powers.KiPower;

public class Atonement extends CustomCard {
	public static final String ID = "Atonement";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int KI = 3;
	private static final int UPGRADE_KI = 2;
	private static final int HP_LOSS = 4;

	public Atonement() {
		super(ID, NAME, "img/cards/atonement.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
				CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = KI;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {


			AbstractDungeon.actionManager.addToBottom(new VFXAction(new OfferingEffect(), 0.5F));
			AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, HP_LOSS));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, this.magicNumber), this.magicNumber));

			if(this.upgraded){
				AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 2));
			}else{
				AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
			}


	}



	public AbstractCard makeCopy() {
		return new Atonement();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_KI);
			rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}
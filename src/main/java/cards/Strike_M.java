package cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import patches.AbstractCardEnum;
import basemod.*;
import powers.KiPower;

public class Strike_M extends CustomCard {
	public static final String ID = "Strike_M";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_ATTACK_DMG = 3;
	private static final int KI = 1;

	public Strike_M() {
		super(ID, NAME, "img/cards/strike_m.png", COST, DESCRIPTION, CardType.ATTACK,
				AbstractCardEnum.VIRIDIAN, CardRarity.BASIC,
				CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.tags.add(AbstractCard.CardTags.STRIKE);
		this.tags.add(BaseModCardTags.BASIC_STRIKE);
		this.magicNumber = this.baseMagicNumber = KI;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.BLUNT_LIGHT));

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, this.magicNumber), this.magicNumber));

	}



	public AbstractCard makeCopy() {
		return new Strike_M();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_ATTACK_DMG);
		}
	}
}
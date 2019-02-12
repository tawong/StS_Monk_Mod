package character;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.abstracts.CustomPlayer;
import patches.AbstractCardEnum;
import patches.TheMonkEnum;


public class TheMonk extends CustomPlayer {
	public static final int ENERGY_PER_TURN = 3;
	
	public static final String[] orbTextures = {
			"img/char/monk/orb/layer1.png",
			"img/char/monk/orb/layer2.png",
			"img/char/monk/orb/layer3.png",
			"img/char/monk/orb/layer4.png",
			"img/char/monk/orb/layer5.png",
			"img/char/monk/orb/layer6.png",
			"img/char/monk/orb/layer1d.png",
			"img/char/monk/orb/layer2d.png",
			"img/char/monk/orb/layer3d.png",
			"img/char/monk/orb/layer4d.png",
			"img/char/monk/orb/layer5d.png",
	};

	public static final String SERVANT_MAIN = "img/char/monk/main.png";
	public static final String SERVANT_CORPSE = "img/char/monk/corpse.png";
	public static final String SERVANT_SHOULDER_1 = "img/char/monk/shoulder.png";
	public static final String SERVANT_SHOULDER_2 = "img/char/monk/shoulder2.png";

	public static final float[] layerSpeeds = {
			80.0F, 40.0F, -40.0F, 20.0F, 0.0F,
			16.0F, 8.0F, -8.0F, 5.0F, 0.0F,
	};
	
	public TheMonk(String name, PlayerClass setClass) {
		super(name, setClass, orbTextures, "img/char/monk/orb/vfx.png", layerSpeeds, null, null);
		
		initializeClass(SERVANT_MAIN,
				SERVANT_SHOULDER_2,
				SERVANT_SHOULDER_1,
				SERVANT_CORPSE,
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

	}

	@Override
	public void applyEndOfTurnTriggers() {
		for (AbstractPower p : this.powers) {
			p.atEndOfTurn(true);
		}
	}

	public ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Strike_M");
		retVal.add("Strike_M");
		retVal.add("Strike_M");
		retVal.add("Strike_M");
		retVal.add("Defend_M");
		retVal.add("Defend_M");
		retVal.add("Defend_M");
		retVal.add("Defend_M");
		retVal.add("Assess");
		retVal.add("RapidResponse");
		retVal.add("Flurry");
		retVal.add("ControlledStrike");

//		retVal.add("Wallop");
//		retVal.add("BadgerClaw");
//		retVal.add("SideKick");
//		retVal.add("DragonClaw");
//		retVal.add("PrimalForm");
//		retVal.add("BatClaw");
//		retVal.add("QuickShots");
//		retVal.add("Divinity");


		return retVal;
	}
	
	public ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("SuspiciousFlask");
		retVal.add("ImbuedPendant");
//		retVal.add("Wallet");
//		retVal.add("Champagne");
//		retVal.add("Celery");
//		retVal.add("BoStaff");
//		retVal.add("BoxingGloves");
//		retVal.add("BrassKnuckles");
//		retVal.add("ChangePurse");
//		retVal.add("GreenJuice");
//		retVal.add("HolyBook");
//		retVal.add("HolySymbol");
//		retVal.add("JadeIdol");
//		retVal.add("Keg");
//		retVal.add("SatinRobe");
//		retVal.add("SpiritJar");
//		retVal.add("StainedGlass");
//		retVal.add("Tent");
		UnlockTracker.markRelicAsSeen("ImbuedPendant");
		UnlockTracker.markRelicAsSeen("SuspiciousFlask");
//		UnlockTracker.markRelicAsSeen("Wallet");
//		UnlockTracker.markRelicAsSeen("Champagne");
//		UnlockTracker.markRelicAsSeen("Celery");
		return retVal;
	}


	public CharSelectInfo getLoadout() {

			return new CharSelectInfo("The Monk", "A disciple of ancient martial arts. NL The Monk uses Ki to empower their attacks.",
				65, 65, 0, 99, 5, this, getStartingRelics(), getStartingDeck(), false);
	}

	public void doCharSelectScreenSelectEffect() {
		CardCrawlGame.sound.playV("BELL", 1.75f);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
	}

	public int getAscensionMaxHPLoss() {
		return 5;
	}

	public CardColor getCardColor() {
		return AbstractCardEnum.VIRIDIAN;
	}

	public Color getCardRenderColor() {
		return CardHelper.getColor(0.0f, 79.0f, 0.0f);
	}

	public Color getCardTrailColor() {
		return CardHelper.getColor(0.0f, 79.0f, 0.0f);
	}

	public String getCustomModeCharacterButtonSoundKey() {
		return "BELL";
	}

	public BitmapFont getEnergyNumFont() {
		return FontHelper.energyNumFontGreen;
	}

	public String getLocalizedCharacterName() {
		String char_name;

			char_name = "The Monk";

		return char_name;
	}

	public Color getSlashAttackColor() {
		return CardHelper.getColor(131.0f, 156.0f, 165.0f);
	}

	public AttackEffect[] getSpireHeartSlashEffect() {
		return new AttackEffect[]{
		        AttackEffect.SLASH_DIAGONAL,
		        AttackEffect.SLASH_HORIZONTAL,
		        AttackEffect.SLASH_DIAGONAL,
		        AttackEffect.SLASH_VERTICAL,
		        AttackEffect.SLASH_HORIZONTAL,
		        AttackEffect.SLASH_VERTICAL
		    };
	}

	public String getSpireHeartText() {
		return com.megacrit.cardcrawl.events.beyond.SpireHeart.DESCRIPTIONS[10];
	}

	public AbstractCard getStartCardForEvent() {
		return new Strike_Red();
	}

	public String getTitle(PlayerClass arg0) {
		String title;

			title = "The Monk";

		return title;
	}

	public String getVampireText() {
		return com.megacrit.cardcrawl.events.city.Vampires.DESCRIPTIONS[1];
	}

	public AbstractPlayer newInstance() {
		return new TheMonk(this.name, TheMonkEnum.THE_MONK);
	}
}

package net.entetrs.xenon.artefacts.managers;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.entetrs.xenon.artefacts.extra.Bonus;
import net.entetrs.xenon.artefacts.extra.BonusType;
import net.entetrs.xenon.artefacts.friendly.Ship;
import net.entetrs.xenon.commons.libs.SoundAsset;

/**
 * gestionnaire des bonus.
 * 
 * @author robin
 *
 */
public class BonusManager
{
	private List<Bonus> bonuses = new LinkedList<>();

	private static BonusManager instance = new BonusManager();

	public static BonusManager getInstance()
	{
		return instance;
	}

	private BonusManager()
	{
		// protection
	}

	public void addBonus(BonusType bonusType, float x, float y)
	{
		Bonus bonus = new Bonus(bonusType, bonusType.getLifeForce(), bonusType.getLifeForce(), x, y, bonusType.getVX(), bonusType.getVY());
		bonuses.add(bonus);
	}

	public void render(SpriteBatch batch, float delta)
	{
		bonuses.forEach(bonus -> bonus.render(batch, delta));
		bonuses.removeIf(e -> e.getBoundingCircle().y < -e.getBoundingCircle().radius || !e.isAlive());
	}

	/**
	 * vérifie les collisions des bonus avec le vaisseau. Si tel est le cas, le
	 * bonus est "capturé" par le vaisseau, et le bonus est traité, en fonction
	 * de son type.
	 * 
	 * @param ship
	 */
	public void checkBonus(Ship ship)
	{
		for (Bonus bonus : bonuses)
		{
			if (bonus.isCollision(ship))
			{
				processBonus(ship, bonus);
			}
		}
	}

	/**
	 * traite le bonus en fonction de son type. modifie l'état du vaisseau en
	 * fonction du bonus.
	 * 
	 * @param ship
	 * 
	 * @param bonus
	 */
	public void processBonus(Ship ship, Bonus bonus)
	{
		switch (bonus.getType())
		{
			case NORMAL_BONUS:
				break;
			case POWER_UP_BONUS:
				ship.increaseLife(10); 
				break;
			default:
		}
		bonus.grab();
		SoundAsset.BONUS.play();	
	}
}

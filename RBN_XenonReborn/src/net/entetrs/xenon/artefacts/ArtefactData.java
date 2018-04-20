package net.entetrs.xenon.artefacts;

public class ArtefactData
{
	/**
	 * points de vie de l'artefact.
	 */
	private final int maxLifePoints;

	/**
	 * points de vie de l'artefact.
	 */
	private int lifePoints;
	/**
	 * force d'impact à appliquer lors des collisions.
	 */
	private final int impactForce;
	/**
	 * vitesse de dépaclement sur l'axe des X.
	 */
	private float vectorX;
	/**
	 * vitesse de déplacement sur l'axe des Y.
	 */
	private float vectorY;


	public ArtefactData(int lifePoints, int impactForce, float vectorX, float vectorY)
	{
		super();
		this.maxLifePoints = lifePoints;
		this.lifePoints = lifePoints;
		this.impactForce = impactForce;
		this.vectorX = vectorX;
		this.vectorY = vectorY;
	}

	public int getMaxLifePoints()
	{
		return maxLifePoints;
	}

	public int getLifePoints()
	{
		return lifePoints;
	}


	public void setLifePoints(int lifePoints)
	{
		this.lifePoints = (lifePoints > maxLifePoints) ? maxLifePoints : lifePoints;
	}


	public float getVectorX()
	{
		return vectorX;
	}


	public void setVectorX(float vectorX)
	{
		this.vectorX = vectorX;
	}


	public float getVectorY()
	{
		return vectorY;
	}


	public void setVectorY(float vectorY)
	{
		this.vectorY = vectorY;
	}


	public int getImpactForce()
	{
		return impactForce;
	}





}

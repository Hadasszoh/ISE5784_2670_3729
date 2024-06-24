package primitives;

/**
 * Material class represents the material of a shape in the scene.
 */
public class Material {
	public Double3 kD = Double3.ZERO;
	public Double3 kS = Double3.ZERO;
	public int nShininess = 1;

	/**
	 * Sets the kD value.
	 *
	 * @param  kD  the new kD value
	 * @return     the updated Material object
	 */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * Sets the kD value.
	 *
	 * @param  kD  the new kD value
	 * @return     the updated Material object
	 */
	public Material setKd(double kD) {
		this.kD = new Double3(kD);
		return this;
	}
	/**
	 * Sets the kS value of the material.
	 *
	 * @param  kS  the new kS value to set
	 * @return     the updated Material object
	 */
	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}
	/**
	 * Sets the kS value of the material.
	 *
	 * @param  kS  the new kS value to set
	 * @return     the updated Material object
	 */
	public Material setKs(double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	/**
	 * Sets the shininess of the material.
	 *
	 * @param  nShininess  the new shininess value
	 * @return             the updated Material object
	 */
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
	/**
	 * Sets the shininess of the material.
	 *
	 * @param  nShininess  the new shininess value
	 * @return             the updated Material object
	 */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
}

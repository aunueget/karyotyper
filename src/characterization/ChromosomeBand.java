package characterization;

public class ChromosomeBand {
	public enum BandType {
		BLACK, WHITE, GREY, CENTROMERE
	}

	private final BandType type;
	private final int length;
	
	public ChromosomeBand(BandType type, int length) {
		if (type == BandType.CENTROMERE && length == 0) {
			throw new IllegalArgumentException("Length of a centromere band cannot be non-zero.");
		}
		
		this.type = type;
		this.length = length;
	}

	public BandType type() {
		return type;
	}
	
	public int length() {
		return length;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + length;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChromosomeBand other = (ChromosomeBand) obj;
		if (length != other.length)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
}

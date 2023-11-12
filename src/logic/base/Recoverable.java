package logic.base;

/**
 * This interface defines methods for the Vegetable that can be recovered.
 */
public interface Recoverable {
	/**
	 * This method will be called when the Vegetable has recovered.
	 */
	void recover();

	/**
	 * This boolean checks if the Vegetable is able to be recoverable or not.
	 * 
	 * @return true is vegetable can recover
	 */
	boolean isRecoverable();
}

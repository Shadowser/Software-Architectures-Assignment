package softarch.portal.app;

import softarch.portal.db.IDatabaseFacade;


/**
 * This class is an abstract superclass for all <i>managers</i>.
 * @author Niels Joncheere
 */
public abstract class Manager {
	protected IDatabaseFacade dbFacade;
}

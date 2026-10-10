package de.vw.mib.asl.internal.androidauto.target;

import org.dsi.ifc.androidauto2.Constants;
import org.dsi.ifc.navigation.BapManeuverDescriptor;

public class NavTurn {
    private int turnSide;
    private int event;
    private int turnAngle;
    private int turnNumber;

    public NavTurn(int turnSide, int event, int turnAngle, int turnNumber) {
        this.turnSide = turnSide;
        this.event = event;
        this.turnAngle = turnAngle;
        this.turnNumber = turnNumber;
    }

    public int getTurnSide() {
        return turnSide;
    }

    public int getEvent() {
        return event;
    }

    public int getTurnAngle() {
        return turnAngle;
    }

    public int getTurnNumber() {
        return turnNumber;
    }


    public BapManeuverDescriptor toManeuverDescriptor() {
        BapManeuverDescriptor maneuverDescriptor = new BapManeuverDescriptor();

        if (event == Constants.NAVIGATIONTURNEVENT_DEPART) {
            maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.DIRECTION_TO_DESTINATION;
            maneuverDescriptor.direction = ManeuverDescriptorDirection.STRAIGHT;
            return maneuverDescriptor;
        }

        if (event == Constants.NAVIGATIONTURNEVENT_TURN) {
            maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.TURN;
            if (turnSide == Constants.NAVIGATIONTURNSIDE_LEFT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.LEFT;
            }
            if (turnSide == Constants.NAVIGATIONTURNSIDE_RIGHT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.RIGHT;
            }
            return maneuverDescriptor;
        }

        if (event == Constants.NAVIGATIONTURNEVENT_SLIGHT_TURN) {
            maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.TURN;
            if (turnSide == Constants.NAVIGATIONTURNSIDE_LEFT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.LEFT_SLIGHT;
            }
            if (turnSide == Constants.NAVIGATIONTURNSIDE_RIGHT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.RIGHT_SLIGHT;
            }
            return maneuverDescriptor;
        }

        if (event == Constants.NAVIGATIONTURNEVENT_SHARP_TURN) {
            maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.TURN;
            if (turnSide == Constants.NAVIGATIONTURNSIDE_LEFT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.LEFT_SHARP;
            }
            if (turnSide == Constants.NAVIGATIONTURNSIDE_RIGHT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.RIGHT_SHARP;
            }
            return maneuverDescriptor;
        }

        if (event == Constants.NAVIGATIONTURNEVENT_OFF_RAMP) {
            if (turnSide == Constants.NAVIGATIONTURNSIDE_LEFT) {
                maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.EXIT_LEFT;
                maneuverDescriptor.direction = ManeuverDescriptorDirection.LEFT;
            }
            if (turnSide == Constants.NAVIGATIONTURNSIDE_RIGHT) {
                maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.EXIT_RIGHT;
                maneuverDescriptor.direction = ManeuverDescriptorDirection.RIGHT;
            }

            return maneuverDescriptor;
        }

        if (event == Constants.NAVIGATIONTURNEVENT_ON_RAMP) {
            maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.TURN;
            if (turnSide == Constants.NAVIGATIONTURNSIDE_LEFT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.LEFT;
            }
            if (turnSide == Constants.NAVIGATIONTURNSIDE_RIGHT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.RIGHT;
            }

            return maneuverDescriptor;
        }

        if (event == Constants.NAVIGATIONTURNEVENT_MERGE) {
            maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.TURN;
            maneuverDescriptor.direction = ManeuverDescriptorDirection.STRAIGHT;
            return maneuverDescriptor;
        }

        if (event == Constants.NAVIGATIONTURNEVENT_NAME_CHANGE) {
            maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.TURN;
            maneuverDescriptor.direction = ManeuverDescriptorDirection.STRAIGHT;
            return maneuverDescriptor;
        }

        if (event == Constants.NAVIGATIONTURNEVENT_STRAIGHT) {
            maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.TURN;
            maneuverDescriptor.direction = ManeuverDescriptorDirection.STRAIGHT;
            return maneuverDescriptor;
        }

        if (event == Constants.NAVIGATIONTURNEVENT_FORK) {
            if (turnSide == Constants.NAVIGATIONTURNSIDE_LEFT || turnSide == Constants.NAVIGATIONTURNSIDE_RIGHT) {
                maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.FORK_2;
            }
            if (turnSide == Constants.NAVIGATIONTURNSIDE_LEFT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.LEFT;
            }
            if (turnSide == Constants.NAVIGATIONTURNSIDE_RIGHT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.RIGHT;
            }
            if (turnSide != Constants.NAVIGATIONTURNSIDE_LEFT && turnSide != Constants.NAVIGATIONTURNSIDE_RIGHT) {
                maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.FORK_3;
                maneuverDescriptor.direction = ManeuverDescriptorDirection.STRAIGHT;
            }
            return maneuverDescriptor;
        }

        if (event == Constants.NAVIGATIONTURNEVENT_U_TURN) {
            maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.UTURN;
            if (turnSide == Constants.NAVIGATIONTURNSIDE_LEFT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.LEFT;
            }
            if (turnSide == Constants.NAVIGATIONTURNSIDE_RIGHT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.RIGHT;
            }
            return maneuverDescriptor;
        }

        if (event == Constants.NAVIGATIONTURNEVENT_ROUNDABOUT_ENTER) {
            maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.PREPARE_ROUNDABOUT;
            maneuverDescriptor.direction = ManeuverDescriptorDirection.STRAIGHT;
            return maneuverDescriptor;
        }

        if (event == Constants.NAVIGATIONTURNEVENT_ROUNDABOUT_EXIT) {
            if (turnSide == Constants.NAVIGATIONTURNSIDE_LEFT) {
                maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.EXIT_ROUNDABOUT_TRS_LEFT;
                maneuverDescriptor.direction = ManeuverDescriptorDirection.LEFT;
            }
            if (turnSide == Constants.NAVIGATIONTURNSIDE_RIGHT) {
                maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.EXIT_ROUNDABOUT_TRS_RIGHT;
                maneuverDescriptor.direction = ManeuverDescriptorDirection.RIGHT;
            }
			// Передаем номер съезда (если поле доступно в структуре)											 
            maneuverDescriptor.roundaboutExitNumber = this.turnNumber; 
            return maneuverDescriptor;
        }

        if (event == Constants.NAVIGATIONTURNEVENT_ROUNDABOUT_ENTER_AND_EXIT) {
            if (turnSide == Constants.NAVIGATIONTURNSIDE_LEFT) {
                maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.ROUNDABOUT_TRS_LEFT;
            }
            if (turnSide == Constants.NAVIGATIONTURNSIDE_RIGHT) {
                maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.ROUNDABOUT_TRS_RIGHT;
            }
            maneuverDescriptor.direction = calculateRoundaboutDirection();
			// КРИТИЧЕСКОЕ ИСПРАВЛЕНИЕ: Передаем номер съезда на приборную панель														  
            maneuverDescriptor.roundaboutExitNumber = this.turnNumber; 
            return maneuverDescriptor;
        }

        if (event == Constants.NAVIGATIONTURNEVENT_DESTINATION) {
            maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.NEAR_DESTINATION;
            if (turnSide == Constants.NAVIGATIONTURNSIDE_LEFT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.LEFT;
            }
            if (turnSide == Constants.NAVIGATIONTURNSIDE_RIGHT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.RIGHT;
            }
            if (turnSide != Constants.NAVIGATIONTURNSIDE_LEFT && turnSide != Constants.NAVIGATIONTURNSIDE_RIGHT) {
                maneuverDescriptor.direction = ManeuverDescriptorDirection.STRAIGHT;
            }
            return maneuverDescriptor;
        }

        maneuverDescriptor.mainElement = ManeuverDescriptorMainElement.NO_SYMBOL;
        maneuverDescriptor.direction = ManeuverDescriptorDirection.STRAIGHT;
        return maneuverDescriptor;
    }

    private int calculateRoundaboutDirection() {
        // Заменяем неточную формулу автора на надежный секторный маппинг угла съезда
        int angle = this.turnAngle % 360;
        if (angle < 0) angle += 360;
																   

        if (angle >= 45 && angle < 135) {
            return 192; // Right (Соответствует разметке BAP VAG)
        } else if (angle >= 135 && angle < 225) {
            return 0;   // Straight
        } else if (angle >= 225 && angle < 315) {
            return 64;  // Left
        } else {
            return 128; // U-Turn
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        NavTurn navTurn = (NavTurn) obj;

        if (turnSide != navTurn.turnSide) return false;
        if (event != navTurn.event) return false;
        if (turnAngle != navTurn.turnAngle) return false;
        return turnNumber == navTurn.turnNumber;
    }

    public int hashCode() {
        int result = turnSide;
        result = 31 * result + event;
        result = 31 * result + turnAngle;
        result = 31 * result + turnNumber;
        return result;
    }
}

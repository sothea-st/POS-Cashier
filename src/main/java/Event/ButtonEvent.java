package Event;

/**
 *
 * @author FRONT-END.06
 */
public interface ButtonEvent {

     default public void onClick() {
     }

     default public void onDelete() {
     }

     default public void onEditQty(int qty) {
     }

     default public void onMouseClick() {
     }

     default public void onKeyType() {
     }

     default public void onKeyRelease() {
     }

     default public void onKeyPress() {
     }

     default public void onFocusGain() {

     }

     default public void btnPlus() {
     }

     default public void btnMinus() {
     }

     default public void onSelect(String key) {
     }

     default public void onSelectBreadcrumb(String key,String value) {
     }
     
     default public void onRemove(String key) {
     }
}

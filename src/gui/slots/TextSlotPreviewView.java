package gui.slots;

import gui.SlotView;
import model.slots.ImageSlot;
import model.slots.TextSlot;
import org.w3c.dom.Text;
import utils.StringUtils;
import windows.frames.MainFrame;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextSlotPreviewView extends SlotView {
    TextSlot slot;

    public TextSlotPreviewView(TextSlot slot){
        this.slot = slot;
    }

    public TextSlot getSlot() {
        return slot;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(slot.getBackgroundColor());
        int width = MainFrame.getInstance().getProjectView().getOpenPresentationView().getNavigationView().getComponent(0).getWidth();
        int height = MainFrame.getInstance().getProjectView().getOpenPresentationView().getNavigationView().getComponent(0).getHeight();
        g.fillRect((int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), (int)(width*slot.getSize().getWidth()), (int)(height*slot.getSize().getHeight()));
        g.setColor(Color.black);
        System.out.println((width*slot.getSize().getWidth()));
        /*if((width*slot.getSize().getWidth())<4) return;
        String text= slot.getText();
        java.util.List<String> textList = new ArrayList<>();
        FontMetrics fm = g.getFontMetrics(g.getFont());
        for(int i = 1; i<=100;i++){
            g.setFont(new Font(Font.MONOSPACED , (slot.isBold()? Font.BOLD:Font.PLAIN)|(slot.isItalic()?Font.ITALIC:Font.PLAIN), i));
            Map<TextAttribute, Integer> fontAttributes = new HashMap<>();
            fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            if(slot.isUnderline()){
                g.setFont(g.getFont().deriveFont(fontAttributes));
            }


            fm= g.getFontMetrics(g.getFont());
            Rectangle2D rect=fm.getStringBounds(text,g);
            List<String> nova= StringUtils.wrap(text, fm, (int)(width*slot.getSize().getWidth())-4);
            if((nova.size())*fm.getHeight()<=(int)(height*slot.getSize().getHeight())){
                textList = nova;
            }
            else {
                break;
            }
        }
        for (int i = 0;i<textList.size();i++){
            g.drawString(textList.get(i), (int)(width*slot.getPosition().getX())+2, (int)(height*slot.getPosition().getY())+(i+1)*fm.getHeight());
        }*/
    }

    public void setSlot(TextSlot slot) {
        this.slot = slot;
    }
}

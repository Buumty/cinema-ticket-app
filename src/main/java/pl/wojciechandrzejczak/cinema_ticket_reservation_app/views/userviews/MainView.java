package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.userviews;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.SeanceService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.UserFooter;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.UserHeader;

@UIScope
@Route("")
public class MainView extends VerticalLayout {
    private final SeanceService seanceService;
    @Autowired
    public MainView(SeanceService seanceService) {
        this.seanceService = seanceService;
        setSizeFull();
        setPadding(true);
        setSpacing(true);
        add(new UserHeader());
        add(seanceVirtualList());
        add(new UserFooter());
    }

    private VirtualList<Seance> seanceVirtualList() {
        VirtualList<Seance> seanceVirtualList = new VirtualList<>();
        seanceVirtualList.setSizeFull();
        seanceVirtualList.setItems(seanceService.findAllSeances());
        seanceVirtualList.setRenderer(seanceComponentRenderer);
        return seanceVirtualList;
    }
    private final ComponentRenderer<Component, Seance> seanceComponentRenderer = new ComponentRenderer<>(seanceComponentRenderer -> {

        HorizontalLayout seanceLayout = new HorizontalLayout();
        seanceLayout.setSpacing(true);
        seanceLayout.setPadding(true);
        seanceLayout.setSizeFull();
        seanceLayout.setAlignItems(Alignment.CENTER);
        seanceLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        seanceLayout.add(new Div(new Text(seanceComponentRenderer.getMovie().getName())));
        seanceLayout.add(new Div(new Text(seanceComponentRenderer.getMovie().getDescription())));
        seanceLayout.add(new Div(new Text(seanceComponentRenderer.getStartTime().toString())));

        return seanceLayout;
    });

}

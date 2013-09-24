package com.actimind.diagnostic.fw;

import com.actimind.diagnostic.view.MonitorAction;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher {

    private ServletContext context;
    private ServletRequest request;
    private HttpServletResponse response;

    public Dispatcher(ServletContext context, ServletRequest request, HttpServletResponse response) {
        this.context = context;
        this.request = request;
        this.response = response;
    }

    public Dispatcher(ServletContext context, ServletRequest request) {
        this.context = context;
        this.request = request;
    }

    public Dispatcher(ServletContext context, HttpServletResponse response) {
        this.context = context;
        this.response = response;
    }

    public MonitorAction monitor() {
        MRenderer r = new MRenderer();
        MonitorAction v = new MonitorAction(r);
        r.setAction(v);

        v.setDbUrl(context.getInitParameter("db-url"));
        v.setConnectorClass(context.getInitParameter("connector-class"));

        return v;
    }

    class MRenderer implements Renderer {
        private Action action;

        public void setAction(Action action) {
            this.action = action;
        }

        public void redirect(String jsp) throws IOException {
            response.sendRedirect(jsp);
        }

        public void render() {
            if (request != null)
                request.setAttribute("view", action.getView());
        }

        public void download(String fname) {
            render();
            if (response != null)
                response.setHeader("Content-disposition", "attachment; filename=" + fname);
        }

    }

}

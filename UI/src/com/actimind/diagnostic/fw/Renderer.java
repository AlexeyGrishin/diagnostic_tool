package com.actimind.diagnostic.fw;

import java.io.IOException;

public interface Renderer {

    public void redirect(String jsp) throws IOException;

    public void render();

    public void download(String fname);
}

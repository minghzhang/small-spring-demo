package com.louis.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @date : 2021/8/17
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}

package io.github.tyb.common.test.util;

import io.github.tyb.common.test.pojo.types.QuotePost;
import io.github.tyb.common.test.pojo.types.TextPost;

public class PostVisitor {
    void visit(TextPost textPost) { }
    void visit(QuotePost quotePost) { }
}

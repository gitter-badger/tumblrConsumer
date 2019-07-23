package io.github.tyb.common.util;

import io.github.tyb.common.pojo.types.QuotePost;
import io.github.tyb.common.pojo.types.TextPost;

public class PostVisitor {
    void visit(TextPost textPost) { }
    void visit(QuotePost quotePost) { }
}

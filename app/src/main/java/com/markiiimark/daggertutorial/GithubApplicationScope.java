package com.markiiimark.daggertutorial;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by MarkiiimarK on 5/10/17.
 */

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface GithubApplicationScope {
}

Title: Java Optional Controversy
Date: 2024-05-29
Tags: programming, java

I have worked at a company and on a team where `Optional` is used extensively for a long time.
So, I was very surprised when I found out that `Optional` is a [controversial topic](https://nipafx.dev/inside-java-newscast-19/).
There is even a camp that believes [`Optional` should be avoided](https://blogs.oracle.com/javamagazine/post/optional-class-null-pointer-drawbacks).
Brian Goetz, the Java Architect, explicitly advocates the ["Use Optional as a Return Value in Limited Cases" camp](https://stackoverflow.com/questions/26327957/should-java-8-getters-return-optional-type/26328555#26328555) on Stack Overflow.
This Stack Overflow post was written in 2014, and comments are still active even in 2024.

All this controversy confused me a lot.
My current team follows Brian Goetz's opinion,
and it is not very easy to persuade them to use `Optional` more broadly whenever needed.

Then, I found this comment in Brian Goetz's post, and my mind became clear:
> Java did not invent Optional. Neither does Java's Optional type do anything special than its usage in other languages.

Yes, `Optional` (or `Option` or `Maybe`) probably predates Java.
Java users used custom `Optional` types (e.g., Guava) even before `java.util.Optional` was added.
The opinion of the _Java Architect_ doesn't need to be considered the golden rule in this case.
Actually, there are other opinions even within the Java core team as mentioned [here](https://nipafx.dev/inside-java-newscast-19/).

I know that Java `Optional` has issues.
It has memory overhead. It can make code look uglier. It is sometimes not very ergonomic to use.
It is not very well integrated with older Java libraries.

However, it is still Java's best weapon against `NullPointerException`.


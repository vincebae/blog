Title: Fibonacci Tail Recursion
Date: 2024-06-17
Tags: programming, fp, javascript, clojure

# Loop -> Tail Recursion Fibonacci

Fibonacci tail recursion in JavaScript and Clojure.
Conversion from iteration to recursion is done in the same ways as the [basic conversion](https://blog.biniko.me/0005-loop-to-tail-recursion-basic.html).
Some steps are skipped to make the conversion concise.

### Non-tail recursion version
```
// Recursion, but not tail call. Simple and easy to understand, but very slow.
// Note that negative numbers are not handled correctly to make it simpler throughout this post.
function fib(n) {
  if (n <= 1) return n;
  return fib(n - 1) + fib(n - 2);
}
```

### Iteration version
```
function fib(n) {
  let curr = 0;
  let next = 1;
  let i = 1;
  while (i <= n) {
    let prev = curr;
    curr = next;
    next = prev + curr;
    i++;
  }
  return curr;
}
```

### Intermediate step before tail recursion
```
// - move loop condition to inside of while
// - return termination case early
// - use destructuring to assign variables
// - make index descreasing
function fib(n) {
  let [i, curr, next] = [n, 0, 1];
  while (true) {
    if (i <= 0) return curr;
    [i, curr, next] = [i - 1, next, curr + next];
  }
}
```
### Unnecessary step: add label and continue
```
function fib(n) {
  let [i, curr, next] = [n, 0, 1];
  loop: while (true) {
    if (i <= 0) return curr;
    [i, curr, next] = [i - 1, next, curr + next];
    continue loop;
  }
}
```

### Tail recursion with inner function
```
function fib(n) {
  const loop = (i, curr, next) => {
    if (i <= 0) return curr;
    return loop(i - 1, next, curr + next);
  }
  return loop(n, 0, 1);
}
```

### Tail recursion with default parameters
```
function fib(n, curr = 0, next = 1) {
  if (n <= 0) return curr;
  return fib(n - 1, next, curr + next);
}
```

### One liner
```
const fib = (n, curr = 0, next = 1) => n <= 0 ? curr : fib(n - 1, next, curr + next);
```

### Clojure Multi-arity
```
;;; Note that the condition is flipped from the JS version above.
(defn fib
  ([n] (fib n 0 1))
  ([n curr next] (if (pos? n) (recur (dec n) next (+ curr next)) curr)))
```

### Clojure Loop
```
(defn fib [n]
  (loop [i n, curr 0, next 1]
    (if (pos? i) (recur (dec i) next (+ curr next)) curr)))
```

Title: Convert Loop to Tail Recursion Basic
Date: 2024-06-07
Tags: programming, fp, clojure, javascript

Converting very basic loop to tail recursion using JavaScript and Clojure at the end.

## For-loop version

```
// Sum integer from 1 to n (inclusive).
function sumTo(n) {
  let res = 0;
  for (i = 1; i <= n; i++) {
    res += i;
  }
  return res;
}
```

## While-loop version

```
// For-loop is a syntactic sugar for while loop.
// Any for-loop can be changed to while loop.
function sumTo(n) {
  let res = 0;
  let i = 1;
  while (i <= n) {
    res += i;
    i++;
  }
  return res;
}
```

## While (true) loop version

```
// We can move the condition of while loop to inside the loop with break
function sumTo(n) {
  let res = 0;
  let i = 1;
  while (true) {
    if (i <= n) {
      res += i;
      i++;
    } else {
      break;
    }
  }
  return res;
}
```

## Make it return early in while loop
```
function sumTo(n) {
  let res = 0;
  let i = 1;
  while (true) {
    if (i > n) {
      return res
    }    
    res += i;
    i++;
  }
  // This is unreachable, return statement here can be removed
}
```

## Make the index descending instead of ascending
```
function sumTo(n) {
  let res = 0;
  let i = n;
  while (true) {
    if (i <= 0) {
      return res;
    }    
    res += i;
    i--;
  }
}
```

## Use destructuring
```
function sumTo(n) {
  let [res, i] = [0, n];
  while (true) {
    if (i <= 0) {
      return res;
    }    
    [res, i] = [res + i, i - 1];
  }
}
```

## Use labeled statement and continue
```
function sumTo(n) {
  let [res, i] = [0, n];
  loop: while (true) {
    if (i <= 0) {
      return res;
    }    
    [res, i] = [res + i, i - 1];
    continue loop; // Sure, this is unnecessary, but added for the next step
  }
}
```

## Use labeled statement and continue (Commented)
```
function sumTo(n) {
  let [res, i] = [0, n]; // Initialization. These will be the initial value for the inner recursion function
  loop: while (true) { // This will become the inner recursion function which take (res, i) as parameters
    if (i <= 0) { // termination condition of the recursion
      return res;
    }    
    [res, i] = [res + i, i - 1]; // These are the new values of res and i when not terminated.
                                 // These will be carried to the next step (recursive call).
    continue loop; // This will be recursive call with new params from the above line
  }
}
```

## Tail-Recursion Version
```
function sumTo(n) {
  // Inner function for tail recursion.
  const loop = (res, i) => {
    if (i <= 0) { // Same termination condition. No need to change
      return res;
    }
    // Recursive call with values for next step.
    return loop(res + i, i - 1);
  }
  // Params are same as the initial values from the loop version.
  return loop(0, n);
}
```

## Tail-Recursion Version with flipped parameter order
```
function sumTo(n) {
  const loop = (i, res) => {
    if (i <= 0) {
      return res;
    }
    return loop(i - 1, res + i);
  }
  return loop(n, 0);
}
```

## Tail-Recursion Version with default parameter (no inner function)
```
// Initial value of the previous version is set as the default value of res.
// Since the inner function (loop) is removed, sumTo will be directly called.
// We don't need variable i anymore. It is replaced by n.
function sumTo(n, res = 0) {
  if (n <= 0) {
    return res;
  }
  return sumTo(n - 1, res + n);
}
```

## One liner with tertiary operation
```
const sumTo = (n, res = 0) => (n <= 0) ? res : sumTo(n - 1, res + n);
```

## Clojure version Multi-arity Version
```
;; Clojure doesn't support default parameters.
;; Multi-arity function can be used instead of inner function.
;; In clojure, recur needs to be used to perform tail recursion with tail-call optimization.
(defn sumTo
 ([n] (sumTo n 0))  ; call with res = 0
 ([n res]
   (if (<= n 0)  ; termination condition 
     res  ; return res when terminated
     (recur (dec n) (+ res n)))))  ; recursive case
```

## Clojure version Loop Version
```
;; Loop macro can be used to have tail recursion in the middle of the function.
(defn sumTo
 [n]
 ;; initialize the loop with i to n and res to 0
 ;; this becomes the target for recur below
 ;; comma is unnecessary, but added for clarity
 (loop [i n, res 0]
   (if (<= i 0) res (recur (dec i) (+ res i)))))
```



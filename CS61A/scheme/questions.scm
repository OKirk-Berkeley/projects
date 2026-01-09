(define (caar x) (car (car x)))
(define (cadr x) (car (cdr x)))
(define (cdar x) (cdr (car x)))
(define (cddr x) (cdr (cdr x)))

;; Problem 15
;; Returns a list of two-element lists
(define (enumerate s)
  ; BEGIN PROBLEM 15
  (define (helper s n) (if (null? s) s (cons (cons n (cons (car s) nil)) (helper (cdr s) (+ n 1)))))
  (helper s 0)
  )
  ; END PROBLEM 15

;; Problem 16

;; Merge two lists S1 and S2 according to ORDERED? and return
;; the merged lists.
(define (merge ordered? s1 s2)
  ; BEGIN PROBLEM 16
  (define (helper ordered? s1 s2 fin) (cond ((null? s1) (append fin s2))
                                            ((null? s2) (append fin s1))
                                            ((ordered? (car s1) (car s2)) (helper ordered? (cdr s1) s2 (append fin (cons (car s1) nil))))
                                            (else (helper ordered? s1 (cdr s2) (append fin (cons (car s2) nil))))
  ))
  (cond ((null? s1) s2)
        ((null? s2) s1)
        ((ordered? (car s1) (car s2)) (helper ordered? (cdr s1) s2 (cons (car s1) nil)))
        (else (helper ordered? s1 (cdr s2) (cons (car s2) nil)))
  )
)
  ; END PROBLEM 16

;; Optional Problem 2

;; Returns a function that checks if an expression is the special form FORM
(define (check-special form)
  (lambda (expr) (equal? form (car expr))))

(define lambda? (check-special 'lambda))
(define define? (check-special 'define))
(define quoted? (check-special 'quote))
(define let?    (check-special 'let))

;; Converts all let special forms in EXPR into equivalent forms using lambda
(define (let-to-lambda expr)
  (cond ((atom? expr)
         ; BEGIN OPTIONAL PROBLEM 2
         expr
         ; END OPTIONAL PROBLEM 2
         )
        ((quoted? expr)
         ; BEGIN OPTIONAL PROBLEM 2
         expr
         ; END OPTIONAL PROBLEM 2
         )
        ((or (lambda? expr)
             (define? expr))
         (let ((form   (car expr))
               (params (cadr expr))
               (body   (cddr expr)))
           ; BEGIN OPTIONAL PROBLEM 2
           (append (list form params) (let-to-lambda body))
           ; END OPTIONAL PROBLEM 2
           ))
        ((let? expr)
         (let ((values (cadr expr))
               (body   (cddr expr)))
           ; BEGIN OPTIONAL PROBLEM 2
           (append (cons (cons 'lambda (cons (car (zip values)) (cons (car body) nil))) nil) (car (cdr (zip values))))
           ; END OPTIONAL PROBLEM 2
           ))
        (else
         ; BEGIN OPTIONAL PROBLEM 2
         (map let-to-lambda expr)
         ; END OPTIONAL PROBLEM 2
         )))

; Some utility functions that you may find useful to implement for let-to-lambda

(define (zip pairs)
    (define (helper pairs so_far)
        (if (null? pairs) so_far (helper (cdr pairs)
        (cons (cons (car (car pairs)) (car so_far)) (cons (cons (car (cdr (car pairs))) (car (cdr so_far))) nil))
        ))
    )
    (define (reverse s) (if (null? (cdr s)) (cons (car s) nil) (append (reverse (cdr s)) (cons (car s) nil))))
    (define helped (helper pairs (cons nil (cons nil nil))))
    (cons (reverse (car helped)) (cons (reverse (car (cdr helped))) nil))
)
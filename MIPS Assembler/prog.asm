
.text
move $s0, $t0
sll $t0, $t1, 3
L1: add $t0, $t0, $s1
addi $t0, $t0, 10
ori $t0, $t0, 10
beq $s0, $t0, EXIT
beq $s0, $t0, EXIT
EXIT:

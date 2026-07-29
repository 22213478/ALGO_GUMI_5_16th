#### 시간복잡도 `O(N^2)`

> 
>    Checkpoint : N x N 행렬이 주어질 때,<br>
>    시계 방향으로 90도, 180도, 270도 회전한 모양
>    
>    N, i를 활용해서 출력.
>

```text
for(i: range(N)){
    for(j: range(N)){

    }
}
```
라고 할 때
<br>
90도는 ↑ 방향 [`선 증감`][`증가`]&nbsp;&nbsp;&nbsp;&nbsp;∴ [ N-j ][ i ]
<br>
<br>
180도는 ← 방향 [`증감`][`선 증감`]&nbsp;&nbsp;&nbsp;&nbsp;∴ [ N-i ][ N-j ]
<br>
<br>
270도는 ↓ 방향 [`선 증가`][`증감`]&nbsp;&nbsp;&nbsp;&nbsp;∴ [ j ][ N-i ]
package ch03;

/**
 * 거리두기 확인하기 - Level2
 *
 * [문제]
 * 개발자를 희망하는 죠르디가 카카오에 면접을 보러 왔습니다. 코로나 바이러스 감염 예방을 위해 응시자들은
 * 거리를 둬서 대기를 해야하는데 개발 직군 면접인 만큼 아래와 같은 규칙으로 대기실에 거리를 두고 앉도록 안내하고 있습니다.
 * 1. 대기실은 5개이며, 각 대기실은 5 x 5 크기입니다.
 * 2. 거리두기를 위하여 응시자들끼리는 맨해튼 거리가 2 이하로 앉지 말아 주세요.
 * 3. 단 응시자가 앉아 있는 자리 사이가 파티션으로 막혀 있을 경우에는 허용합니다.
 *
 * 5개의 대기실을 본 죠르디는 각 대기실에서 응시자들이 거리두기를 잘 지키고 있는지 알고 싶어졌습니다.
 * 자리에 앉아 있는 응시자들의 정보와 대기실 구조를 대기실별로 담은 2차원 문자원 배열 places 가 매개변수로 주어집니다.
 * 각 대기실별로 거리두기를 지키고 있으면 1을, 한 명이라도 지키지 않고 있으면 0을 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 *
 * [제한사항]
 * places 의 행 길이(대기실 개수) = 5
 *  - places 의 각 행은 하나의 대기실 구조를 나타냅니다.
 * places 의 열 길이(대기실 세로 길이) = 5
 * places 의 원소는 P,O,X로 이루어진 문자열입니다.
 *  - places 원소의 길이(대기실 가로 길이) = 5
 *  - P는 응시자가 앉아있는 자리를 의미합니다.
 *  - O는 빈 테이블을 의미합니다.
 *  - X는 파티션을 의미합니다.
 * 입력으로 주어지는 5개 대기실의 크기는 모두 5 x 5 입니다.
 * return 값 형식
 *  - 1차원 정수 배열에 5개의 원소를 담아서 return 합니다.
 *  - places 에 담겨 있는 5개 대기실의 순서대로, 거리두기 준수 여부를 차례대로 배열에 담습니다.
 *  - 각 대기실 별로 모든 응시자가 거리두기를 지키고 있으면 1을, 한 명이라도 지키지 않고 있으면 0을 담습니다.
 * 정확성 테스트 : 10초
 *
 *  * 맨해튼 거리 = 두 테이블 T1, T2가 행렬(r1,c1),(r2,c2)에 각각 위치하고 있다면 T1과 T2 사이의 맨해튼 거리는 |r1-r2| + |c1-c2| 이다.
 */
public class CheckDistance {
    // 문제 풀이 흐름
    // 문제에서는 여러 대기실이 입력으로 주어지지만 하나의 대기실에 대한 문제 풀이 흐름을 정의하면 이를 각 대기실에 적용할 수 있다.
    // 1. 대기실의 모든 응시자 위치에 대해 반복
    //  1-A. 상하좌우 중 빈 테이블이 있는 방향에 대해 1-B로 진행
    //  1-B. 빈 테이블과 인접한 위치 중 응시자가 있다면 거리두기를 지키지 않은 것
    // 2. 모든 응시자의 위치를 검사했으나 거리두기를 지키지 않은 경우를 발견하지 못했으면 거리두기를 지킨 것

    // 0번째 - 위로 이동, 1번째 - 좌로 이동, 2번째 - 우로 이동, 3번째 - 아래로 이동
    private static final int dx[] = {0, -1, 1, 0};  // x의 변화량
    private static final int dy[] = {-1, 0, 0, 1};  // y의 변화량

    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < answer.length; i++) {
            String[] place = places[i]; // 1개의 대기실
            char[][] room = new char[place.length][]; // 1개의 대기실의 5x5 구조를 담은 배열
            for (int j = 0; j < room.length; j++) {
                room[j] = place[j].toCharArray();   // 각 열에 원소를 담는다
            }
            // 거리두기 검사 후 answer에 기록
            if (isDistanced(room)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }
    // 거리두기 검사
    private static boolean isDistanced(char[][] room) {
        // 대기실의 모든 응시자 위치에 대해 반복
        for (int y = 0; y < room.length; y++) {
            for (int x = 0; x < room[y].length; x++) {
                // 응시자의 위치가 아니면 검사X
                if (room[y][x] != 'P') continue;
                // 거리두기 검사
                if (!isDistanced(room, x, y)) {return false;}
            }
        }
        return true;
    }
    // 응시자의 위치(x,y)가 거리두기를 지키는지 검사
    private static boolean isDistanced(char[][] room, int x, int y) {

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length) {continue;}
            // room[y][x]가 거리두기를 지키는지 검사
            switch (room[ny][nx]) {
                case 'P' : return false;
                case 'O' :
                    if (isNextToVolunteer(room, nx, ny, 3 - d)) {
                        return false;
                    }
                break;
            }
        }

        return true;
    }

    // 1-B. 빈 테이블과 인접한 위치 중 응시자가 있는지 확인 (있다면 거리두기를 지키지 않은 것)
    private static boolean isNextToVolunteer(char[][] room, int x, int y, int exclude) {
        for (int d = 0; d < 4; d++) {
            if(d==exclude) continue; // 원래 검사를 시작했던 응시자가 위치한 자리는 검사하지 않아야 하므로 패스

            int nx = x + dx[d];
            int ny = y + dy[d];
            // 이동할 위치가 범위를 벗어나지 않는지 체크
            if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length) { continue; }
            // 빈 테이블과 인접한 위치에 응시자가 있으므로 거리두기를 지키지 않음
            if (room[ny][nx] == 'P') { return true; }
        }

        return false;
    }

    public static void main(String[] args) {

    }
}

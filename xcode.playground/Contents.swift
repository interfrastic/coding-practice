// Complete the matrixRotation function below.
func matrixRotation(matrix: [[Int]], r: Int) -> Void {
    func rotateRing(_ matrix: inout [[Int]],
                    _ iMin: Int, _ iMax: Int,
                    _ jMin: Int, _ jMax: Int) {
        let temp = matrix[iMin][jMin]
        
        for j in jMin...jMax - 1 {
            matrix[iMin][j] = matrix[iMin][j + 1]
        }
        
        for i in iMin...iMax - 1 {
            matrix[i][jMax] = matrix[i + 1][jMax]
        }
        
        for j in (jMin + 1...jMax).reversed() {
            matrix[iMax][j] = matrix[iMax][j - 1]
        }
        
        for i in (iMin + 1...iMax).reversed() {
            matrix[i][jMin] = matrix[i - 1][jMin]
        }
        
        matrix[iMin + 1][jMin] = temp
    }
    
    func rotateMatrix(matrix: inout [[Int]], r: Int) {
        let m = matrix.count
        let n = matrix[0].count
        let iMax = m - 1
        let jMax = n - 1
        let ringIndexMax = min(m, n) / 2 - 1
        
        for ringIndex in 0...ringIndexMax {
            let ringIMin = ringIndex
            let ringIMax = iMax - ringIndex
            let ringJMin = ringIndex
            let ringJmax = jMax - ringIndex
            
            let ringElementCount = 2 * (ringIMax - ringIMin)
                + 2 * (ringJmax - ringJMin)
            let equivalentRotationCount = r % ringElementCount
            
            if equivalentRotationCount != 0 {
                for _ in 1...equivalentRotationCount {
                    rotateRing(&matrix,
                               ringIMin, ringIMax,
                               ringJMin, ringJmax)
                }
            }
        }
    }
    
    func printMatrix(matrix: [[Int]]) {
        for row in matrix {
            print(row.map{String($0)}.joined(separator: " "))
        }
    }
    
    var mutableMatrix = matrix
    
    rotateMatrix(matrix: &mutableMatrix, r: r)
    
    printMatrix(matrix: mutableMatrix)
}

let matrix = [[101, 102, 103, 104],
              [105, 106, 107, 108],
              [109, 110, 111, 112],
              [113, 114, 115, 116]]

matrixRotation(matrix: matrix, r: 100 * 4 * 12)

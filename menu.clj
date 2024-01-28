(ns A3
  (:require [clojure.java.io :as io]
            [clojure.string :as str])

; reads the frequency.txt
(defn devide-tokens []
  (let [file-path "frequency.txt"]
    (with-open [reader (io/reader file-path)]
      (->> reader
           (line-seq)
           (mapcat #(str/split % #"\s+"))
           (vec))))); Convert the sequence of words into a vector


; creates a map based on frequency.txt
(defn generate-map []
  (let [word-frequencies (distinct (devide-tokens))]
    (zipmap word-frequencies (range))))

; This reads the file as a string 
(defn read_file [file-path]
  (try
    (-> (slurp file-path)
        (str/lower-case)) ; Convert the string to lowercase because The doesn't give 0 until it's the 
    (catch Exception e
      (println (str "Error reading file: " (.getMessage e)))
      (read_file file-path)))) ; I need it to return this 

; This is basically what compresses based on the string and the zipmap I have
(defn compress [input-str my-map]
  ; Splits the string into tokens using whitespace as a delimiter
  (let [tokens (str/split input-str #"\s+")]
    ;Here if its a number -> @number@
    (map #(if (number? (read-string %))
            (str "@", %, "@")
            ;Here in my map if we don't find the value, the default is to simply print the key
            (get my-map % %)) tokens)))


; This is where we will write to a file 
(defn write-to-file [file-path content]
  (spit file-path content))



; This is the map used to decompress
(defn generate-map-decompress []
  ; This distinct helped get the perfect matching
  (let [word-frequencies (distinct (devide-tokens))]
    (zipmap  (range) word-frequencies))); I need it to return this so that I can do function composition


; This is the function that decompress the  compressed file  based on the map
(defn decompress [input-str my-map]
  ; Split the string into tokens using whitespace as a delimiter
  (let [tokens (str/split input-str #"\s+")]
   (clojure.string/join " " (map #(get my-map (read-string %) %) tokens))))


; Display the menu and ask the user for the option
(defn showMenu
  []
  (println "\n\n*** Compression Menu ***")
  (println "------------------\n")
  (println "1. Display list of files")
  (println "2. Display file contents")
  (println "3. Compress a file")
  (println "4. Uncompress a file")
  (println "5. Exit")
  (do
    (print "\nEnter an option? ")
    (flush)
    (read-line)))



; Display all files in the current folder
(defn option1 []
  (println "")
  (println "File List:")
  ; Basically, it checks in the current directory of the folder for the list of files and prints them
  (let [current-folder (System/getProperty "user.dir")]
    (doseq [file (file-seq (io/file current-folder))]
      (when (.isFile file)
        (println (str "*./" (.getName file)))))))



; Read and display the file contents (if the file exists). Java's File class can be used to 
; check for existence first. 
(defn option2
  []
  (print "\nPlease enter a file name => ")
  (flush)
  ; We store  the file name read in the directory 
  (let [file_name (read-line)]
    (try
      ; This store was is read in file-name 
      (let [sentence (slurp file_name)]
        ; This prints it 
        (println "\n" sentence))
      ; This catches the error and display a message to handle it
      (catch java.io.FileNotFoundException e
        (println (str "\nOops: File " file_name " does not exist"))))))



; Compress the (valid) file provided by the user. You will replace the println expression with code 
; that calls your compression function
(defn option3 []
  (print "\nPlease enter a file name => ")
  (flush)
  (let [file-name (read-line)
        new-file (str file-name ".ct")]
    (try
      (let [input (read_file file-name)
            result-map (generate-map)]
        (let [content (compress input result-map)]
          ; Writing in the new file with .ct extension the compressed version 
          (write-to-file new-file (clojure.string/join " " content))))
      ; If it doesn't exist , prints an error message
      (catch Exception e
        (println (str "\nOops: File " file-name " does not exist"))))))


; Decompress the (valid) file provided by the user. You will replace the println expression with code 
; that calls your decompression function
(defn option4
  [] ;parm(s) can be provided here, if needed
  (print "\nPlease enter a file name => ")
  (flush)
  (let [file-name (read-line)
        input (read_file file-name)
        result-map (generate-map-decompress)]
    (let [content (decompress input result-map)] 
      (println  "\nThis is the decompressed version in"   file-name)
      (println "\n")
      (println content))))


; If the menu selection is valid, call the relevant function to 
; process the selection
(defn processOption
  [option] ; other parm(s) can be provided here, if needed
  (if (= option "1")
    (option1)
    (if (= option "2")
      (option2)
      (if (= option "3")
        (option3)  ; other args(s) can be passed here, if needed
        (if (= option "4")
          (option4)   ; other args(s) can be passed here, if needed
          (println "Invalid Option, please try again"))))))


; Display the menu and get a menu item selection. Process the
; selection and then loop again to get the next menu selection
(defn menu
  [] ; parm(s) can be provided here, if needed
  (let [option (str/trim (showMenu))]
    (if (= option "5")
      (println "\nGood Bye\n")
      (do
        (processOption option)
        (recur)))))   ; other args(s) can be passed here, if needed



; Run the program. You might want to prepare the data required for the mapping operations
; before you display the menu. You don't have to do this but it might make some things easier

(menu) ; other args(s) can be passed here, if needed

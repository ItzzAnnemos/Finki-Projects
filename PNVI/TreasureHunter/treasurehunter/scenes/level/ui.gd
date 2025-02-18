extends Control

@onready var timer_label = $TimerLabel
@onready var timer = $Timer  
@onready var game_over_screen = $GameOverScreen  # Reference to Game Over UI
@onready var game_won_screen = $GameWonScreen  # Reference to Game Won UI
@onready var score_label = $ScoreLabel  # Ensure the path is correct

var time_left = 180  # Set initial time in seconds
var collectibles_count = 0  # Number of collectibles
var total_collectibles = 10  # Adjust this to the total number in your game

func _ready():
	timer.start()
	update_timer_label()
	game_over_screen.visible = false  # Hide Game Over screen initially
	game_won_screen.visible = false  # Hide Game Won screen initially

func update_timer_label():
	timer_label.text = "Time: " + str(time_left) + " seconds"

func _on_timer_timeout():    
	if time_left > 0:
		time_left -= 1
		update_timer_label()
		collect_item()
	
	if time_left <= 0:
		show_game_over()  # Show game over screen

func collect_item():
	var collected = get_collected_items()  

	if collected >= total_collectibles:
		show_game_won()


func get_collected_items():
	var score_text = score_label.text  # Get text from label
	var score_value = int(score_text.split(":")[-1])  # Extract number after "Score:"
	return score_value / 10
	

func show_game_over():
	timer.stop()  # Stop the timer
	game_over_screen.visible = true  # Show the game over UI
	Input.set_mouse_mode(Input.MOUSE_MODE_VISIBLE)  # Unlock the mouse cursor

func show_game_won():
	timer.stop()  # Stop the timer
	game_won_screen.visible = true  # Show the game won UI
	Input.set_mouse_mode(Input.MOUSE_MODE_VISIBLE)  # Unlock the mouse cursor

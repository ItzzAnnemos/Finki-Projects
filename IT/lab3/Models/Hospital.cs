using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace MVC_lab2.Models
{
    public class Hospital
    {
        public Hospital() {
            Doctors = new List<Doctor>();
        }

        [Key]
        public int Id { get; set; }

        [Required]
        public String Name { get; set; }

        [Required]
        public String Address { get; set; }

        public String ImageUrl { get; set; }

        public virtual ICollection<Doctor> Doctors { get; set; }
    }
}